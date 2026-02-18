package com.tomazbr9.cvlab.modules.users.service;

import com.tomazbr9.cvlab.modules.users.dto.UserPatchDTO;
import com.tomazbr9.cvlab.modules.users.dto.UserResponseDTO;
import com.tomazbr9.cvlab.modules.users.entity.User;
import com.tomazbr9.cvlab.modules.users.exception.UserNotFoundException;
import com.tomazbr9.cvlab.modules.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired UserRepository userRepository;

    public UserResponseDTO getUser(String username){

        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return new UserResponseDTO(user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public UserResponseDTO putUser(UserPatchDTO request, String email){

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        if (request.email() != null && !request.email().isBlank()){
            user.setEmail(request.email());
        }

        if(request.password() != null && !request.password().isBlank()){
            user.setPassword(request.password());
        }

        User userUpdate = userRepository.save(user);

        return new UserResponseDTO(userUpdate.getFirstName(), userUpdate.getLastName(), userUpdate.getEmail());
    }

    public void deleteUser(String email){

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        userRepository.delete(user);

    }

}
