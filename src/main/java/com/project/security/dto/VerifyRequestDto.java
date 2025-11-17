package com.project.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyRequestDto {
    private String id;
    private String code;
}
