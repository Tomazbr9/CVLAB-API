package com.tomazbr9.buildprice.service;

import com.tomazbr9.buildprice.dto.sinapi.ImportResponseDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class AdminService {

    @Value("${spring.sinapi.upload-dir}")
    private String uploadDir;

    private JobLauncher jobLauncher;
    private Job sinapiJob;

    List<String> TABS = Arrays.asList("ISD", "ICD", "ISE");

    public AdminService(JobLauncher jobLauncher, Job sinapiJob){
        this.jobLauncher = jobLauncher;
        this.sinapiJob = sinapiJob;
    }

    public ImportResponseDTO importSinapi(MultipartFile file, String tab) {

        if(file.isEmpty()){
            throw new RuntimeException("Arquivo vazio");
        }

        if(!TABS.contains(tab)){
            throw new RuntimeException("Aba não encontrada para importação.");
        }

        try {

            Path tempFile = Files.createTempFile("sinapi-", ".xlsx");
            file.transferTo(tempFile.toFile());

            JobParameters params = new JobParametersBuilder()
                    .addString("tempFile", tempFile.toAbsolutePath().toString())
                    .addString("sheetName", tab)
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution response = jobLauncher.run(sinapiJob, params);

            return new ImportResponseDTO(response.getJobId(), response.getId(), response.getStatus().name());


        } catch (Exception e){
            throw new RuntimeException("Erro ao executar job SINAPI", e);
        }
    }
}
