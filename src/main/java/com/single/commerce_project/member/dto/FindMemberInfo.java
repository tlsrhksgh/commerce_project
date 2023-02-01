package com.single.commerce_project.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMemberInfo {
    private String userId;
    private String userName;
    private String email;
    private String uuid;
    private String password;
}
