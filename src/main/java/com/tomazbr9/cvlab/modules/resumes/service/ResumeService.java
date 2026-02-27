package com.tomazbr9.cvlab.modules.resumes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeRequestDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeResponseDTO;
import com.tomazbr9.cvlab.modules.resumes.entity.Resume;
import com.tomazbr9.cvlab.modules.resumes.repository.ResumeRepository;
import com.tomazbr9.cvlab.modules.users.entity.User;
import com.tomazbr9.cvlab.modules.users.exception.UserNotFoundException;
import com.tomazbr9.cvlab.modules.users.repository.UserRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
public class ResumeService {

    @Value("classpath:prompts/resume_optimization.st")
    private Resource prompt;

    @Autowired ChatClient chatClient;
    @Autowired UserRepository userRepository;
    @Autowired ResumeRepository resumeRepository;

    public ResumeResponseDTO optimizedResume(ResumeRequestDTO request, UUID userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        if(request.jobDescription() == null || request.jobDescription().isBlank() || request.resume() == null){
            throw new RuntimeException("Dados invalidos para processo de otimização");
        }

        PromptTemplate template = new PromptTemplate(prompt);

        Map<String, Object> model = Map.of(
                "resume", request.resume(),
                "jobDescription", request.jobDescription()
        );

        ResumeDTO resumeOptimized = chatClient.prompt(template.create(model)).call().entity(ResumeDTO.class);

        Resume resume = Resume.builder()
                .jobDescription(request.jobDescription())
                .optimizedJson(resumeOptimized)
                .isPaidSingle(false)
                .createdAt(Instant.now())
                .updateAt(Instant.now())
                .user(user).build();

        resumeRepository.save(resume);

        return new ResumeResponseDTO(resume.getOptimizedJson());

    }
}
