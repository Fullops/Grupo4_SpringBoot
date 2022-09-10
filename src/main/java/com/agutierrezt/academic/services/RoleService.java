package com.agutierrezt.academic.services;

import com.agutierrezt.academic.dtos.response.PermissionResponseDto;
import com.agutierrezt.academic.dtos.response.RoleResponseDto;

import java.util.List;

public interface RoleService {
    List<RoleResponseDto> getAllRoles();
    List<PermissionResponseDto> getAllPermission(String role);
}
