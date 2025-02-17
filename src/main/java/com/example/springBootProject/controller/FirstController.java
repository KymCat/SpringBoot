package com.example.springBootProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") // localhost:8080/hi 접속 -> greetings.mustache 파일을 찾아 반환
    public String niceToMeetYou(Model model) {

        // model 객체가 "유민" 값을 "username"에 연결해 웹 브라우저로 보냄
        model.addAttribute("username", "유민");
        return "greetings"; // greetings.mustache 파일 반환

    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "유민");
        return "goodbye";
    }
}
