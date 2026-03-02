package com.tomazbr9.cvlab.modules.templates.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeDTO;
import com.tomazbr9.cvlab.modules.resumes.entity.Resume;
import com.tomazbr9.cvlab.modules.resumes.repository.ResumeRepository;
import com.tomazbr9.cvlab.modules.users.entity.User;
import com.tomazbr9.cvlab.modules.users.exception.UserNotFoundException;
import com.tomazbr9.cvlab.modules.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Service
public class TemplateService {

    @Autowired SpringTemplateEngine templateEngine;
    @Autowired UserRepository userRepository;

    private byte[] generatePdf(String templateName, ResumeDTO request, boolean showWatermark){

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            Context context = new Context();
            context.setVariable("resume", request);
            context.setVariable("showWatermark", showWatermark);

            String processedHtml = templateEngine.process(templateName + ".html", context);

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(processedHtml, "/");
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        } catch (Exception e){
            throw new RuntimeException("Erro ao gerar o preview do currículo: " + e.getMessage(), e);
        }
    }

    public byte[] getPreview(String templateName, ResumeDTO request, UUID userId){
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return generatePdf(templateName, request, true);
    }

    public byte[] getFinalDownload(String templateName, ResumeDTO request, UUID userId){
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return generatePdf(templateName, request, false);
    }



}
