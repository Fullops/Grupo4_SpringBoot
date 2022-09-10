package com.agutierrezt.academic.services.impl;

import com.agutierrezt.academic.db.entities.Roles;
import com.agutierrezt.academic.db.entities.Users;
import com.agutierrezt.academic.db.repositories.RoleRepository;
import com.agutierrezt.academic.db.repositories.UserRepository;
import com.agutierrezt.academic.dtos.request.CreateUserRequestDto;
import com.agutierrezt.academic.dtos.request.UpdateUserRequestDto;
import com.agutierrezt.academic.dtos.response.CreateUserResponseDto;
import com.agutierrezt.academic.dtos.response.RoleResponseDto;
import com.agutierrezt.academic.dtos.response.UserResponseDto;
import com.agutierrezt.academic.exceptions.EleccionesException;
import com.agutierrezt.academic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public CreateUserResponseDto create(CreateUserRequestDto user) {

        Users userToCreate = Users.builder()
                .email(user.getEmail())
                .firstName(user.getName())
                .lastName(user.getLastName())
                .password(encoder.encode(user.getPassword()))
                .roleId(roleRepo.findOneByName("Estudiante").get().get_id())
                .build();
        userToCreate = repo.save(userToCreate);
        return CreateUserResponseDto.builder()
                .id(userToCreate.get_id())
                .build();
    }

    @Override
    public void delete(String id) {
        repo.findById(id).map(x -> {
            repo.delete(x);
            return x;
        }).orElseThrow(() -> new EleccionesException("Usuario no existe", 404, new Date()));
    }

    @Override
    public void update(UpdateUserRequestDto user, String id) {
        Users userFound =  repo.findById(id).map(x -> {
            return x;
        }).orElseThrow(() -> new EleccionesException("Usuario no existe", 404, new Date()));
        userFound.setFirstName(user.getName() != null ? user.getName() : userFound.getFirstName());
        userFound.setLastName(user.getLastName() != null ? user.getLastName() : userFound.getLastName());
        repo.save(userFound);
    }

    @Override
    public UserResponseDto getById(String id) {
        Users user = repo.findById(id).map(x -> {
            return x;
        }).orElseThrow(() -> new EleccionesException("Usuario no existe", 404, new Date()));

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

    @Override
    public List<UserResponseDto> getUsers(String role) {
        Roles r = roleRepo.findOneByName(role).get();
        List<Users> users = repo.findAllByRoleId(r.get_id());

        List<UserResponseDto> usersToReturn = new ArrayList<>();
        for (Users user: users) {
            usersToReturn.add(
                    UserResponseDto.builder()
                            .role(RoleResponseDto.builder()
                                    .name(r.getName())
                                    .description(r.getDescription())
                                    .build())
                            .email(user.getEmail())
                            .id(user.get_id())
                            .fullName(user.getFirstName() + " " + user.getLastName())
                            .build()
            );
        }
        return usersToReturn;
    }

    @Override
    public List<UserResponseDto> getUsers() {
        List<Users> users = repo.findAll();
        Set<String> roleIds = users.stream().map(x -> x.getRoleId()).collect(Collectors.toSet());
        List<Roles> roles = (List<Roles>) roleRepo.findAllById(roleIds);

        List<UserResponseDto> usersToReturn = new ArrayList<>();
        for (Users user: users) {
            Roles role = roles.stream().filter(x -> x.get_id().equals(user.getRoleId())).collect(Collectors.toList()).get(0);
            usersToReturn.add(
                UserResponseDto.builder()
                        .role(RoleResponseDto.builder()
                                .name(role.getName())
                                .description(role.getDescription())
                                .build())
                        .email(user.getEmail())
                        .id(user.get_id())
                        .fullName(user.getFirstName() + " " + user.getLastName())
                        .build()
            );
        }
        return usersToReturn;
    }
}
