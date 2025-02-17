package com.example.springBootProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") // localhost:8080/hi 접속 -> greetings.mustache 파일을 찾아 반환
    public String niceToMeetYou() {
        return "greetings"; // greetings.mustache 파일 반환

    }
}
