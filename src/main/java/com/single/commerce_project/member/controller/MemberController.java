package com.single.commerce_project.member.controller;

import com.single.commerce_project.member.dto.FindUserIdDto;
import com.single.commerce_project.member.dto.MemberDto;
import com.single.commerce_project.member.dto.ResetPasswordDto;
import com.single.commerce_project.member.service.MemberService;
import com.single.commerce_project.member.util.MemberAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final MemberAuth memberAuth;

    @RequestMapping("/member/login")

    public String login() {
        if (memberAuth.isAuthenticated()) {
            return "redirect:/";
        }

        return "member/login";
    }

    @GetMapping("/member/register")
    public String register() {
        if (memberAuth.isAuthenticated()) {
            return "redirect:/";
        }

        return "member/register";
    }

    @PostMapping("/member/register")
    public String register(Model model, MemberDto member) {

        boolean result = memberService.register(member);
        model.addAttribute("result", result);

        return "member/register_status";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest req) {

        String uuid = req.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email_auth";
    }

    @GetMapping("/member/find-password")
    public String findPassword(Model model) {

        return "member/find_password";
    }

    @PostMapping("/member/find-password")
    public String findPasswordSubmit(Model model, ResetPasswordDto resetPasswordDto) {

        boolean result = memberService.sendResetPassword(resetPasswordDto);
        model.addAttribute("result", result);

        return "member/find_password_result";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(@RequestParam("id") String uuid, Model model) {

        model.addAttribute("uuid", uuid);

        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String submitResetPassword(Model model, ResetPasswordDto resetMemberDto) {

        boolean result = memberService.resetPassword(resetMemberDto);
        model.addAttribute("result", result);

        return "member/reset_password_result";
    }

    @GetMapping("/member/find-id")
    public String findUserId() {

        return "member/find_user_id";
    }

    @PostMapping("/member/find-id")
    public String submitUserId(Model model, FindUserIdDto findUserIdDto) {
        String result = memberService.findUserId(findUserIdDto);
        model.addAttribute("result", result);

        return "member/find_userid_result";
    }
}
