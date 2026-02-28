package com.tomazbr9.cvlab.modules.resumes.service;

import com.tomazbr9.cvlab.modules.resumes.dto.ResumeDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeRequestDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeOptimizedResponseDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeResponseDTO;
import com.tomazbr9.cvlab.modules.resumes.entity.Resume;
import com.tomazbr9.cvlab.modules.resumes.mapper.ResumeMapper;
import com.tomazbr9.cvlab.modules.resumes.repository.ResumeRepository;
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

        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        if(request.jobDescription() == null || request.jobDescription().isBlank() || request.resume() == null){
            throw new RuntimeException("Dados invalidos para processo de otimização");
        }

        Prompt prompt = createTemplate(promptResource, request);

        ResumeDTO resumeOptimized = chatClient.prompt(prompt).call().entity(ResumeDTO.class);

        return new ResumeOptimizedResponseDTO(resumeOptimized);
    }

    public ResumeResponseDTO createResume(ResumeRequestDTO request, UUID userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        Resume resume = Resume.builder()
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
}
