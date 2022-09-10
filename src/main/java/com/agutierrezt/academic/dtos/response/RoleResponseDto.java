package com.agutierrezt.academic.dtos.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Data
public class RoleResponseDto {
    private String name;
    private String description;

}
