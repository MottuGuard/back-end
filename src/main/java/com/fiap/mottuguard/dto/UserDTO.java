package com.fiap.mottuguard.dto;

import com.fiap.mottuguard.model.User;
import com.fiap.mottuguard.model.enums.UserRole;
import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Data
@ToString
public class UserDTO extends RepresentationModel<UserDTO> {

    private String id;
    private String login;
    private String password;
    private UserRole role;

    public UserDTO(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

}
