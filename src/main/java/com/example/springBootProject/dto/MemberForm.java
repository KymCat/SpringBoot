package com.example.springBootProject.dto;

import com.example.springBootProject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private String email;
    private String password;

    public Member toEntity() {
        return new Member(null, email, password);
    }
}
