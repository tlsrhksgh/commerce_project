package com.single.commerce_project.member.service;

import com.single.commerce_project.exception.MemberException;
import com.single.commerce_project.member.domain.Member;
import com.single.commerce_project.member.dto.FindUserIdDto;
import com.single.commerce_project.member.dto.MemberDto;
import com.single.commerce_project.member.dto.ResetPasswordDto;
import com.single.commerce_project.member.repository.MemberRepository;
import com.single.commerce_project.member.util.MailUtil;
import com.single.commerce_project.member.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.single.commerce_project.member.type.ErrorCode.MEMBER_NOT_EMAIL_AUTH;
import static com.single.commerce_project.member.type.ErrorCode.USER_NOT_FOUND;
import static com.single.commerce_project.member.type.MemberStatus.EMAIL_REQ;
import static com.single.commerce_project.member.type.MemberStatus.IN_USE;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MailUtil mailUtil;
    private final PasswordUtil passwordUtil;

    @Override
    public boolean register(MemberDto memberDto) {

        Optional<Member> optionalMember = memberRepository.findById(memberDto.getUserId());
        if(optionalMember.isPresent()) {
            return false;
        }

        String mailAuthKey = mailUtil.generateAuthMailKey();
        String encPassword = passwordUtil.encryptPassword(memberDto.getPassword());

        memberRepository.save(Member.builder()
                .userId(memberDto.getUserId())
                .userName(memberDto.getUserName())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .password(encPassword)
                .emailAuthKey(mailAuthKey)
                .memberStatus(EMAIL_REQ)
                .amount(0)
                .build());

        mailUtil.sendAuthMail(memberDto.getEmail(), mailAuthKey);

        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if(!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();

        if(member.getMemberStatus() == IN_USE) {
            return false;
        }

        member.setMemberStatus(IN_USE);
        member.setEmailAuthAt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }

    @Override
    public boolean sendResetPassword(ResetPasswordDto resetPasswordDto) {

        Member member = memberRepository.findById(resetPasswordDto.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보가 존재하지 않습니다"));

        String mailAuthKey = mailUtil.generateAuthMailKey();

        member.setEmailAuthKey(mailAuthKey);
        memberRepository.save(member);

        String email = resetPasswordDto.getEmail();
        mailUtil.sendResetPasswordAuthMail(email, mailAuthKey);

        return true;
    }

    @Override
    public boolean resetPassword(ResetPasswordDto resetMemberDto) {
        Member member = memberRepository.findByEmailAuthKey(resetMemberDto.getUuid())
                .orElseThrow(() -> new UsernameNotFoundException("해당 정보가 존재하지 않습니다"));

        String encPassword = passwordUtil.encryptPassword(resetMemberDto.getPassword());

        member.setPassword(encPassword);
        memberRepository.save(member);

        return true;
    }

    @Override
    public String findUserId(FindUserIdDto findUserIdDto) {

        Optional<Member> optionalMember = memberRepository.findByUserNameAndEmail(
                findUserIdDto.getUserName(), findUserIdDto.getEmail());
        if(!optionalMember.isPresent()) {
            throw new MemberException(USER_NOT_FOUND);
        }

        Member member = optionalMember.get();

        String userId = member.getUserId();

        return userId;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Member member = memberRepository.findById(username).
                orElseThrow(() -> new UsernameNotFoundException("회원 정보가 존재하지 않습니다"));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if(member.isAdminYn()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);
    }
}
