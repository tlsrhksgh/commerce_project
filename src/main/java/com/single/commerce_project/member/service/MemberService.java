package com.single.commerce_project.member.service;

import com.single.commerce_project.member.dto.FindUserIdDto;
import com.single.commerce_project.member.dto.MemberDto;
import com.single.commerce_project.member.dto.ResetPasswordDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    boolean register(MemberDto member);

    boolean emailAuth(String uuid);

    boolean sendResetPassword(ResetPasswordDto resetPasswordDto);

    boolean resetPassword(ResetPasswordDto resetMemberDto);

    String findUserId(FindUserIdDto findUserIdDto);
}
