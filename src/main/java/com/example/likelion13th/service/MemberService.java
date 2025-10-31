package com.example.likelion13th.service;

import com.example.likelion13th.domain.Member;
import com.example.likelion13th.dto.request.JoinRequestDto;
import com.example.likelion13th.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    // 비밀번호 인코더 DI(생성자 주입)
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinRequestDto joinRequestDto){
        // 해당 name이 이미 존재하는 경우
        if (memberRepository.existsByName(joinRequestDto.getName())){
            throw new IllegalArgumentException("이미 존재하는 회원 이름입니다: " + joinRequestDto.getName());
        }

        // 유저 객체 생성
        Member member = joinRequestDto.toEntity(bCryptPasswordEncoder);

        // 유저 정보 저장
        memberRepository.save(member);
    }

    public Member login(JoinRequestDto joinRequestDto) {
        Member member = memberRepository.findByName(joinRequestDto.getName())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!bCryptPasswordEncoder.matches(joinRequestDto.getPassword(), member.getPassword())) {
            return null;
        }

        return member;
    }
}
