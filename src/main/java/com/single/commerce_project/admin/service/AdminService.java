package com.single.commerce_project.admin.service;

import com.single.commerce_project.member.domain.Member;
import com.single.commerce_project.member.dto.MemberDto;

import java.util.List;

public interface AdminService {

    List<MemberDto> list();

}
