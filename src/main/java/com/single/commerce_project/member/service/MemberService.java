package com.single.commerce_project.member.service;

import com.single.commerce_project.member.dto.MemberDto;
import com.single.commerce_project.member.dto.FindMemberInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    boolean register(MemberDto member);

    boolean emailAuth(String uuid);

    boolean sendResetPassword(FindMemberInfo findMemberInfo);

    boolean resetPassword(FindMemberInfo resetMemberDto);

    String findUserId(FindMemberInfo findUserIdDto);

}
