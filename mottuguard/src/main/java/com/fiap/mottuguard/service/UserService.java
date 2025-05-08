package com.fiap.mottuguard.service;

import com.fiap.mottuguard.dto.UserDTO;
import com.fiap.mottuguard.exception.ResourceNotFoundException;
import com.fiap.mottuguard.model.User;
import com.fiap.mottuguard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO findByLogin(String login) throws ResourceNotFoundException {
        User user = userRepository.findById(login).orElseThrow(()->new ResourceNotFoundException("Usuário não encontrado :/"));
        System.out.println(user);
        UserDTO dto = new UserDTO(user);
        return dto;
    }

//    public UserDTO updateUser(String id, User user){
//        User userToUpdate = userRepository.findById(id).get();
//
//        userToUpdate.setRole(user.getRole());
//
//        return userRepository.save(userToUpdate);
//
//    }

//    public void deleteUser(String id){
//        User userToDelete = userRepository.findById(id);
//        userRepository.delete(userToDelete);
//    }


}
