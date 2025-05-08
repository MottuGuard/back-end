package com.fiap.mottuguard.controller;

import com.fiap.mottuguard.dto.UserDTO;
import com.fiap.mottuguard.model.User;
import com.fiap.mottuguard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<UserDTO> getUser(@RequestBody String login){

        return ResponseEntity.status(HttpStatus.OK).body(userService.findByLogin(login));
    }

}
