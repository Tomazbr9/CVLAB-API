package com.tomazbr9.cvlab.modules.templates.controller;

import com.tomazbr9.cvlab.modules.resumes.dto.ResumeDTO;
import com.tomazbr9.cvlab.modules.templates.service.TemplateService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/templates")
public class TemplateController {

    @Autowired private TemplateService service;

    @PostMapping("/preview/{templateName}")
    public ResponseEntity<byte[]> preview(
            @PathVariable String templateName,
            @RequestBody ResumeDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        byte[] pdfBytes = service.getPreview(templateName, request, userDetails.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("preview.pdf").build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/download/{templateName}")
    public ResponseEntity<byte[]> download(
            @PathVariable String templateName,
            @RequestBody ResumeDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        byte[] pdfBytes = service.getFinalDownload(templateName, request, userDetails.getId());

        String fileName = "curriculo_" + request.fullName() + ".pdf".replace("", "-");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(pdfBytes);
    }


}
