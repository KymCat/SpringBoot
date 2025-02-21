package com.example.springBootProject.controller;

import com.example.springBootProject.dto.MemberForm;
import com.example.springBootProject.entity.Member;
import com.example.springBootProject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String newMemberForm() {
        return "member/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm form) {
        log.info(form.toString());

        // to Entity
        Member member = form.toEntity();
        log.info(member.toString());

        // save
        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "";
    }

}
