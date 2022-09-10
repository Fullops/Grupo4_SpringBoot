package com.agutierrezt.academic.services.impl;

import com.agutierrezt.academic.db.entities.Permission;
import com.agutierrezt.academic.db.entities.PermissionRole;
import com.agutierrezt.academic.db.entities.Roles;
import com.agutierrezt.academic.db.repositories.PermissionRepository;
import com.agutierrezt.academic.db.repositories.PermissionRoleRepository;
import com.agutierrezt.academic.db.repositories.RoleRepository;
import com.agutierrezt.academic.dtos.response.PermissionResponseDto;
import com.agutierrezt.academic.dtos.response.RoleResponseDto;
import com.agutierrezt.academic.exceptions.EleccionesException;
import com.agutierrezt.academic.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repo;

    @Autowired
    private PermissionRoleRepository permissionRoleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<Roles> roles = this.repo.findAll();
        return roles.stream().map(x -> RoleResponseDto.builder()
                    .name(x.getName())
                    .description(x.getDescription())
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<PermissionResponseDto> getAllPermission(String role) {
        Roles r = repo.findOneByName(role).map(x -> x).orElseThrow(() -> new EleccionesException("Invalid role", 400, new Date()));
        String id = r.get_id();
        List<PermissionRole> permissionRoles = permissionRoleRepository.findAllByRoleId(id);
        List<PermissionResponseDto> permissions = new ArrayList<>();
        if(!permissionRoles.isEmpty()) {
            List<String> ids = permissionRoles.stream().map(x -> x.getPermissionId()).collect(Collectors.toList());
            List<Permission> p = permissionRepository.findAllByIds(ids);
            permissions = p.stream().map(x -> PermissionResponseDto.builder()
                    .method(x.getMethod().toString())
                    .url(x.getUrl())
                    .build()).collect(Collectors.toList());
        }
        return permissions;
    }
}
