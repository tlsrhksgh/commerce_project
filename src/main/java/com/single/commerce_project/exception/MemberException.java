package com.single.commerce_project.exception;

import com.single.commerce_project.member.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public MemberException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getDescription();
    }
}
