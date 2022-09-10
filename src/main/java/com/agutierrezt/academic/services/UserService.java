package com.agutierrezt.academic.services;

import com.agutierrezt.academic.dtos.request.CreateUserRequestDto;
import com.agutierrezt.academic.dtos.request.UpdateUserRequestDto;
import com.agutierrezt.academic.dtos.response.CreateUserResponseDto;
import com.agutierrezt.academic.dtos.response.UserResponseDto;

import java.util.List;

public interface UserService {
    CreateUserResponseDto create(CreateUserRequestDto user);
    void delete(String id);
    void update(UpdateUserRequestDto user, String id);
    UserResponseDto getById(String id);
    List<UserResponseDto> getUsers(String role);
    List<UserResponseDto> getUsers();
}
