package com.example.springBootProject.repository;

import com.example.springBootProject.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
