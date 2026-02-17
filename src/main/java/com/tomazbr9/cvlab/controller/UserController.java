package com.tomazbr9.cvlab.controller;

import com.tomazbr9.cvlab.dto.user.UserPatchDTO;
import com.tomazbr9.cvlab.dto.user.UserResponseDTO;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import com.tomazbr9.cvlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        UserResponseDTO user = service.getUser(userDetails.getUsername());
        return ResponseEntity.ok().body(user);

    }

    @PatchMapping
    public ResponseEntity<UserResponseDTO> patchUser(@RequestBody UserPatchDTO request, @AuthenticationPrincipal UserDetailsImpl userDetails){

        UserResponseDTO response = service.putUser(request, userDetails.getUsername());

        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        service.deleteUser(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

}
