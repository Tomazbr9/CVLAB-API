package com.tomazbr9.buildprice.controller;

import com.tomazbr9.buildprice.dto.sinapi.BatchStatusDTO;
import com.tomazbr9.buildprice.dto.sinapi.ImportResponseDTO;
import com.tomazbr9.buildprice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service){
        this.service = service;
    }

    @PostMapping("/sinapi/import")
    public ResponseEntity<ImportResponseDTO> importSinapi(@RequestPart("file") MultipartFile file, @RequestPart("tab") String tab) {

        ImportResponseDTO response = service.importSinapi(file, tab);

        return ResponseEntity.ok(response);


    }

    @GetMapping("/sinapi/status/{jobId}")
    public ResponseEntity<BatchStatusDTO> getImportProcessStatus(@PathVariable Long jobId){

        BatchStatusDTO response = service.getImportProcessStatus(jobId);

        return ResponseEntity.ok(response);
    }

}
