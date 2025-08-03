package com.example.likelion13th.service;

import com.example.likelion13th.domain.Member;
import com.example.likelion13th.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Page<Member> getMembersByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return memberRepository.findAll(pageable);
    }

    // 성인 + 이름 오름차순 + 페이징
    public Page<Member> getAdultMembersSortedByName(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return memberRepository.findByAgeGreaterThanEqualOrderByNameAsc(20, pageRequest);
    }

    // 이름 prefix 필터링
    public List<Member> getMemberByNamePrefix(String prefix){
        return memberRepository.findByNameStartingWith(prefix);
    }
}
