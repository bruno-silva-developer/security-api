package com.dev.securityapi.model.dto;

import com.dev.securityapi.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
