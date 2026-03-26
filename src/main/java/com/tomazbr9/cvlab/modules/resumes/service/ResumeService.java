package com.tomazbr9.cvlab.modules.resumes.service;

import com.tomazbr9.cvlab.modules.resumes.dto.ResumeDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeRequestDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeOptimizedResponseDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeResponseDTO;
import com.tomazbr9.cvlab.modules.resumes.entity.Resume;
import com.tomazbr9.cvlab.modules.resumes.mapper.ResumeMapper;
import com.tomazbr9.cvlab.modules.resumes.repository.ResumeRepository;
import com.tomazbr9.cvlab.modules.subscriptions.entity.Subscription;
import com.tomazbr9.cvlab.modules.subscriptions.enums.PlanType;
import com.tomazbr9.cvlab.modules.subscriptions.repository.SubscriptionRepository;
import com.tomazbr9.cvlab.modules.users.entity.User;
import com.tomazbr9.cvlab.modules.users.exception.UserNotFoundException;
import com.tomazbr9.cvlab.modules.users.repository.UserRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ResumeService {

    @Value("classpath:prompts/resume_optimization.st")
    private Resource promptResource;

    @Autowired ChatClient chatClient;
    @Autowired UserRepository userRepository;
    @Autowired ResumeRepository resumeRepository;
    @Autowired SubscriptionRepository subscriptionRepository;
    @Autowired ResumeMapper mapper;

    public ResumeResponseDTO getResume(UUID resumeId, UUID userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        Resume resume = resumeRepository.findByIdAndUser(resumeId, user).orElseThrow(() -> new RuntimeException("Curriculo não encontrado"));
        return mapper.toDTO(resume);
    }

    public List<ResumeResponseDTO> getResumes(UUID userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        List<Resume> resumes = resumeRepository.findByUser(user);

        return mapper.toDTOList(resumes);
    }

    public ResumeOptimizedResponseDTO optimizedResume(ResumeRequestDTO request, UUID userId) {

        if (request.jobDescription() == null || request.jobDescription().isBlank() ||
                request.resume() == null || request.resume().id() == null) {
            throw new RuntimeException("Dados inválidos para processo de otimização");
        }

        Resume resume = resumeRepository.findById(request.resume().id()).orElseThrow(() -> new RuntimeException("Curriculo não encontrado."));

        if(!checkUserPremium(userId) && !resume.isPaidSingle()){
            throw new RuntimeException("Recurso de otimização apenas para usuários premium ou para cvs comprados");
        }

        if(resume.getAiUsageCount() != 0){
            throw new RuntimeException("O numero de otimizaçoes para esse curriculo foi esgotado");
        }

        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        Prompt prompt = createTemplate(promptResource, request);

        ResumeDTO resumeOptimized = chatClient.prompt(prompt).call().entity(ResumeDTO.class);

        return new ResumeOptimizedResponseDTO(resumeOptimized);
    }

    @Transactional
    public ResumeResponseDTO createResume(ResumeRequestDTO request, UUID userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        int qntResumes = resumeRepository.findByUser(user).size();

        if(checkUserPremium(user.getId()) && qntResumes >= 1){
            throw new RuntimeException("Limite de curriculos excedido, assine o premium para criar mais curriculos");
        }

        Resume resume = Resume.builder()
                .nameResume(request.nameResume())
                .jobDescription(request.jobDescription())
                .optimizedJson(request.resume())
                .isPaidSingle(false)
                .createdAt(Instant.now())
                .updateAt(Instant.now())
                .user(user)
                .build();

        Resume savedResume = resumeRepository.save(resume);

        return mapper.toDTO(savedResume);

    }

    public ResumeResponseDTO updateResume(UUID resumeId, ResumeDTO request, UUID userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        Resume resume = resumeRepository.findByIdAndUser(resumeId, user).orElseThrow(() -> new RuntimeException("Curriculo não encontrado"));

        resume.setOptimizedJson(request);

        Resume savedResume = resumeRepository.save(resume);

        return mapper.toDTO(savedResume);
    }

    public void deleteResume(UUID resumeId, UUID userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        Resume resume = resumeRepository.findByIdAndUser(resumeId, user).orElseThrow(() -> new RuntimeException("Curriculo não encontrado"));
        resumeRepository.delete(resume);
    }

    private Prompt createTemplate(Resource promptResource, ResumeRequestDTO request){

        PromptTemplate template = new PromptTemplate(promptResource);

        Map<String, Object> model = Map.of(
                "resume", request.resume(),
                "jobDescription", request.jobDescription()
        );

        return template.create(model);
    }

    private boolean checkUserPremium(UUID userId){
        Subscription subscription = subscriptionRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));
        return subscription.getPlanType() == PlanType.PREMIUM;
    }
}
