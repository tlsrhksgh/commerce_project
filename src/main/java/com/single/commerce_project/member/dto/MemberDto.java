package com.single.commerce_project.member.dto;

import com.single.commerce_project.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MemberDto {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private LocalDateTime registeredAt;
    private boolean adminYn;
    private long amount;

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .registeredAt(member.getRegisteredAt())
                .adminYn(member.isAdminYn())
                .amount(member.getAmount())
                .build();
    }
}
