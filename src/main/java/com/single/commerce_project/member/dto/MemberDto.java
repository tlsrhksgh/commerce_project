package com.single.commerce_project.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {
    private String userId;
    private String userName;
    private String password;
    private String phone;
    private String email;

}
