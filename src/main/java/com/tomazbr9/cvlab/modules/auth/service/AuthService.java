package com.tomazbr9.cvlab.modules.auth.service;

import com.tomazbr9.cvlab.modules.auth.dto.JwtTokenDTO;
import com.tomazbr9.cvlab.modules.auth.dto.LoginDTO;
import com.tomazbr9.cvlab.modules.subscriptions.entity.Subscription;
import com.tomazbr9.cvlab.modules.subscriptions.enums.PlanType;
import com.tomazbr9.cvlab.modules.subscriptions.enums.StatusSubscription;
import com.tomazbr9.cvlab.modules.subscriptions.repository.SubscriptionRepository;
import com.tomazbr9.cvlab.modules.users.dto.UserRequestDTO;
import com.tomazbr9.cvlab.modules.auth.entity.Role;
import com.tomazbr9.cvlab.modules.users.dto.UserResponseDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AuthService {

    @Autowired JwtTokenService jwtTokenService;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired UserRepository userRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired SubscriptionRepository subscriptionRepository;
    @Autowired SecurityConfiguration securityConfiguration;

    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO request){

        Role roleDefault = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new RoleNotFoundException("Papel de usuário não encontrado"));

        if(userRepository.findByEmail(request.email()).isPresent()){
            throw new EmailAlreadyExistsException("Email ja existe.");
        }

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(securityConfiguration.passwordEncoder().encode(request.password()))
                .roles(Set.of(roleDefault))
                .build();

        Subscription subscription = Subscription.builder()
                .user(user)
                .planType(PlanType.FREE)
                .status(StatusSubscription.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);
        subscriptionRepository.save(subscription);

        return new UserResponseDTO(savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
    }

    public JwtTokenDTO authenticateUser(LoginDTO request){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }

}
