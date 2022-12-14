package com.agutierrezt.academic.db.entities;

import com.agutierrezt.academic.enums.Method;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document()
public class Permission {
    @Id
    private String _id;
    private String url;
    private Method method;
}
