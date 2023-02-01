package com.single.commerce_project.member.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_EMAIL_AUTH("이메일 인증이 완료되지 않았습니다. 이메일 인증을 진행해주세요"),
    USER_NOT_FOUND("사용자 아이디가 없습니다");

    private final String description;
}
