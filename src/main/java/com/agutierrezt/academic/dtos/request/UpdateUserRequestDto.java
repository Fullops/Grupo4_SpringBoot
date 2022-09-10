package com.agutierrezt.academic.dtos.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequestDto {
    @Size(min = 2, message = "Nombre muy corto")
    private String name;
    @Size(min = 3, message = "Apellido muy corto")
    private String lastName;
}
