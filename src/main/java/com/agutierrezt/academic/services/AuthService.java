package com.agutierrezt.academic.services;

import com.agutierrezt.academic.dtos.response.UserResponseDto;

public interface AuthService {
    UserResponseDto login(String email, String password);
}
