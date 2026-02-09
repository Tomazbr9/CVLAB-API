package com.tomazbr9.buildprice.controller;

import com.tomazbr9.buildprice.dto.sinapi.ImportResponseDTO;
import com.tomazbr9.buildprice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;


    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/import")
    public ResponseEntity<ImportResponseDTO> importSinapi(@RequestPart("file") MultipartFile file, @RequestPart("tab") String tab) {

        ImportResponseDTO response = adminService.importSinapi(file, tab);

        return ResponseEntity.ok(response);


    }

}
