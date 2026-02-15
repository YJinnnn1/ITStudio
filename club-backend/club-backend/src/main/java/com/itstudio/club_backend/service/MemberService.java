package com.itstudio.club_backend.service;

import com.itstudio.club_backend.entity.Member;
import com.itstudio.club_backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public List<Member> getActiveMembers() {
        return memberRepository.findByIsActive(true);
    }

    public List<Member> getAlumniMembers() {
        return memberRepository.findByIsActive(false);
    }
}