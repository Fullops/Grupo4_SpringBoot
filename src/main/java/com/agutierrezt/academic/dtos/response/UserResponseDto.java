package com.agutierrezt.academic.dtos.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private String id;
    private String fullName;
    private String email;
    private RoleResponseDto role;
}
