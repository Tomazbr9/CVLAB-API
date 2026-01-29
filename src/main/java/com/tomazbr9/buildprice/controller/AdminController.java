package com.tomazbr9.buildprice.controller;

import com.tomazbr9.buildprice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;


    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/sinapi")
    public ResponseEntity<?> popularWithSinapi(@RequestParam("file") MultipartFile file) throws IOException {

        adminService.popularWithSinapi(file);

        return ResponseEntity.ok("");
    }

}
