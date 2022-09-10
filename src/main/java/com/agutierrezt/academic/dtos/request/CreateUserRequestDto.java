package com.agutierrezt.academic.dtos.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestDto {
    @NotEmpty()
    @NotNull()
    @Size(min = 2, message = "Nombre muy corto")
    private String name;
    @NotEmpty()
    @NotNull()
    @Size(min = 2)
    private String lastName;
    @NotNull()
    @Email(message = "Correo electronico no valido")
    private String email;
    @NotNull()
    @NotEmpty()
    @Size(min = 8)
    private String password;
}
