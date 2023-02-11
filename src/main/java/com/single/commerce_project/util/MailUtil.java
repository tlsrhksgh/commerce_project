package com.single.commerce_project.util;

import com.single.commerce_project.components.MailComponents;
import com.single.commerce_project.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MailUtil {

    private final MailComponents mailComponents;

    public void sendAuthMail(String email, String uuid) {

        String subject = "<쇼핑몰> 사이트 가입을 축하드립니다.";
        String text = "<p>사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";

        mailComponents.sendMail(email, subject, text);
    }

    public String generateAuthMailKey() {
        return UUID.randomUUID().toString().replace("-" , "");
    }

    public void sendResetPasswordAuthMail(String email, String uuid) {

        String subject = "[쇼핑몰] 비밀번호 초기화 메일 입니다. ";
        String text = "<p>쇼핑몰 비밀번호 초기화 메일 입니다.<p>" +
                "<p>아래 링크를 클릭하셔서 비밀번호를 초기화 해주세요.</p>"+
                "<div><a target='_blank' href='http://localhost:8080/member/reset/password?id=" + uuid + "'> 비밀번호 초기화 링크 </a></div>";

        mailComponents.sendMail(email, subject, text);
    }
}
