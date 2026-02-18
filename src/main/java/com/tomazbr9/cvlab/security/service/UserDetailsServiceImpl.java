package com.tomazbr9.cvlab.security.service;

import com.tomazbr9.cvlab.modules.users.entity.User;
import com.tomazbr9.cvlab.modules.users.repository.UserRepository;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), user.getRoles(), true);
    }

}
