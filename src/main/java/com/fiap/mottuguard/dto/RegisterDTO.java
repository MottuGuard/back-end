package com.fiap.mottuguard.dto;

import com.fiap.mottuguard.model.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
