package com.single.commerce_project.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDto {
    private String userId;
    private String email;
    private String uuid;
    private String password;
}
