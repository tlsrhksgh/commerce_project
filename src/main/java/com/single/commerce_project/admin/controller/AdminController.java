package com.single.commerce_project.admin.controller;

import com.single.commerce_project.admin.service.AdminService;
import com.single.commerce_project.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin/main")
    public String main() {

        return "admin/main";
    }

    @GetMapping("/admin/member/info")
    public String getMemberInfo(Model model) {

        List<MemberDto> members = adminService.list();

        model.addAttribute("members", members);

        return "/admin/member/member_info";
    }

    @PostMapping("/admin/member/info")
    public String postMemberInfo() {

        return "/admin/member/member_info";
    }

}
