package com.single.commerce_project.admin.service;

import com.single.commerce_project.member.domain.Member;
import com.single.commerce_project.member.dto.MemberDto;
import com.single.commerce_project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{

    private final MemberRepository memberRepository;

    @Override
    public List<MemberDto> list() {

        List<Member> members = memberRepository.findAllByOrderByRegisteredAtDesc();

        return members.stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }

}
