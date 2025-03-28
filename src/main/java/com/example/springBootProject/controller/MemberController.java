package com.example.springBootProject.controller;

import com.example.springBootProject.dto.MemberForm;
import com.example.springBootProject.entity.Member;
import com.example.springBootProject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

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

        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("member id = " + id);
        Member memberEntity = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", memberEntity);
        return "member/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        ArrayList<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);

        return "member/index";
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("계정 삭제 요청이 들어왔습니다!!");

        Member target = memberRepository.findById(id).orElse(null);
        if (target != null) {
            log.info(target.toString());
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "계정 삭제가 완료되었습니다.");
        }

        return "redirect:/members";
    }
}
