package com.tomazbr9.cvlab.modules.templates.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeDTO;
import com.tomazbr9.cvlab.modules.resumes.entity.Resume;
import com.tomazbr9.cvlab.modules.resumes.repository.ResumeRepository;
import com.tomazbr9.cvlab.modules.subscriptions.entity.Subscription;
import com.tomazbr9.cvlab.modules.subscriptions.enums.PlanType;
import com.tomazbr9.cvlab.modules.subscriptions.repository.SubscriptionRepository;
import com.tomazbr9.cvlab.modules.templates.entity.Template;
import com.tomazbr9.cvlab.modules.templates.repository.TemplateRepository;
import com.tomazbr9.cvlab.modules.users.entity.User;
import com.tomazbr9.cvlab.modules.users.exception.UserNotFoundException;
import com.tomazbr9.cvlab.modules.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class TemplateService {

    @Autowired SpringTemplateEngine templateEngine;
    @Autowired UserRepository userRepository;
    @Autowired SubscriptionRepository subscriptionRepository;
    @Autowired ResumeRepository resumeRepository;
    @Autowired TemplateRepository templateRepository;

    private byte[] generatePdf(String templateName, ResumeDTO request, boolean showWatermark){

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            Context context = new Context();
            context.setVariable("resume", request);
            context.setVariable("showWatermark", showWatermark);

            String processedHtml = templateEngine.process(templateName, context);

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(processedHtml, "/");

            builder.useFont(
                    () -> {
                        try {
                            return new ClassPathResource("fonts/CrimsonPro-Regular.ttf").getInputStream();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    "Crimson Pro"
            );
            builder.useFont(
                    () -> {
                        try {
                            return new ClassPathResource("fonts/CrimsonPro-Bold.ttf").getInputStream();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    "Crimson Pro",
                    700,
                    com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder.FontStyle.NORMAL,
                    true
            );

            builder.useFont(
                    () -> {
                        try {
                            return new ClassPathResource("fonts/SourceSans3-Regular.ttf").getInputStream();
                        } catch (IOException e) {
                            throw new RuntimeException("Erro ao carregar SourceSans3-Regular", e);
                        }
                    },
                    "Source Sans 3"
            );

            builder.useFont(
                    () -> {
                        try {
                            return new ClassPathResource("fonts/SourceSans3-Bold.ttf").getInputStream();
                        } catch (IOException e) {
                            throw new RuntimeException("Erro ao carregar SourceSans3-Bold", e);
                        }
                    },
                    "Source Sans 3",
                    700,
                    com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder.FontStyle.NORMAL,
                    true
            );

            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        } catch (Exception e){
            throw new RuntimeException("Erro ao gerar o preview do currículo: " + e.getMessage(), e);
        }
    }

    public String getPreview(String templateName, ResumeDTO request, UUID userId){
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        try {
            Context context = new Context();
            context.setVariable("resume", request);
            context.setVariable("showWatermark", true);

            return templateEngine.process(templateName + ".html", context);

        } catch (Exception e){
            throw new RuntimeException("Erro ao gerar o preview do currículo: " + e.getMessage(), e);
        }
    }


    public byte[] getFinalDownload(String templateName, ResumeDTO request, UUID userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        boolean hasPremiumBenefit = userHasPremiumBenefit(user, request.id());

        boolean showWatermark = !hasPremiumBenefit;

        return generatePdf(templateName, request, showWatermark);
    }

    public List<Template> getTemplates(){
        return templateRepository.findAll();
    }

    private boolean userHasPremiumBenefit(User user, UUID resumeId){

        Subscription subscription = subscriptionRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Curriculo não ecnontrado"));

        boolean isPremium = subscription.getPlanType() == PlanType.PREMIUM;
        boolean resumeWasPurchased = resume.isPaidSingle();

        return isPremium || resumeWasPurchased;
    }
}
