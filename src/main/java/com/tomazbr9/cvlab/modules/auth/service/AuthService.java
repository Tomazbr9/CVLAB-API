package com.tomazbr9.cvlab.modules.auth.service;

import com.tomazbr9.cvlab.modules.auth.dto.JwtTokenDTO;
import com.tomazbr9.cvlab.modules.auth.dto.LoginDTO;
import com.tomazbr9.cvlab.modules.users.dto.UserRequestDTO;
import com.tomazbr9.cvlab.modules.auth.entity.Role;
import com.tomazbr9.cvlab.modules.users.entity.User;
import com.tomazbr9.cvlab.modules.auth.enums.RoleName;
import com.tomazbr9.cvlab.modules.auth.exception.EmailAlreadyExistsException;
import com.tomazbr9.cvlab.modules.auth.exception.RoleNotFoundException;
import com.tomazbr9.cvlab.modules.auth.repository.RoleRepository;
import com.tomazbr9.cvlab.modules.users.repository.UserRepository;
import com.tomazbr9.cvlab.security.SecurityConfiguration;
import com.tomazbr9.cvlab.security.jwt.JwtTokenService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    @Autowired JwtTokenService jwtTokenService;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired UserRepository userRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired SecurityConfiguration securityConfiguration;

    public void registerUser(UserRequestDTO request){

        Role roleDefault = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new RoleNotFoundException("Papel de usuário não encontrado"));

        if(userRepository.findByEmail(request.email()).isPresent()){
            throw new EmailAlreadyExistsException("Email ja existe.");
        }

        User user = new User(null, request.firstName(), request.lastName(), request.email(), securityConfiguration.passwordEncoder().encode(request.password()), Set.of(roleDefault));

        userRepository.save(user);
    }

    public JwtTokenDTO authenticateUser(LoginDTO request){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }

}
