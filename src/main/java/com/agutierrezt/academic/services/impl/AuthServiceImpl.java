package com.agutierrezt.academic.services.impl;

import com.agutierrezt.academic.db.entities.Roles;
import com.agutierrezt.academic.db.entities.Users;
import com.agutierrezt.academic.db.repositories.RoleRepository;
import com.agutierrezt.academic.db.repositories.UserRepository;
import com.agutierrezt.academic.dtos.response.RoleResponseDto;
import com.agutierrezt.academic.dtos.response.UserResponseDto;
import com.agutierrezt.academic.exceptions.EleccionesException;
import com.agutierrezt.academic.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserResponseDto login(String email, String password) {
        Users user = userRepo.findOneByEmail(email).map(x -> x).orElseThrow(() -> new EleccionesException("Usuario no encontrado", 404,new Date()));
        if (!encoder.matches(password, user.getPassword())) throw new EleccionesException("Contraseña invalida", 401, new Date());
        Roles role = roleRepo.findById(user.getRoleId()).get();

        return UserResponseDto.builder()
                .role(RoleResponseDto.builder()
                        .name(role.getName())
                        .description(role.getDescription())
                        .build())
                .email(user.getEmail())
                .id(user.get_id())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .build();
    }
}
