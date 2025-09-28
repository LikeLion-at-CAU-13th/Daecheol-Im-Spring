package com.example.likelion13th.service;

import com.example.likelion13th.domain.Member;
import com.example.likelion13th.enums.Role;
import com.example.likelion13th.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();

        IntStream.rangeClosed(1, 30).forEach(i -> {
            Member member = Member.builder()
                    .name("user" + i)
                    .email("user" + i + "@test.com")
                    .address("서울시 테스트동 " + i + "번지")
                    .phoneNumber("010-1234-56" + String.format("%02d", i))
                    .deposit(1000 * i)
                    .isAdmin(false)
                    .role(Role.BUYER)
                    .age(i) // 나이도 같이 설정
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    void testGetMembersByPage() {
        Page<Member> page = memberService.getMembersByPage(0, 10);

        assertThat(page.getContent()).hasSize(10);
        assertThat(page.getTotalElements()).isEqualTo(30);
        assertThat(page.getTotalPages()).isEqualTo(3);
        assertThat(page.getContent().get(0).getName()).isEqualTo("user1");
    }

    // 성인 + 이름 오름차순 + 페이징
    @Test
    void testGetAdultMemberSortedByName(){
        Page<Member> page = memberService.getAdultMembersSortedByName(0, 10);

        assertThat(page.getContent()).allMatch(m -> m.getAge() >= 20);
        assertThat(page.getContent().get(0).getName()).isEqualTo("user20"); // 오름차순 확인
        assertThat(page.getTotalElements()).isEqualTo(11); // 20~30살 = 11명

    }

    // 이름 prefix 필터링
    @Test
    void testGetMemberByNamePrefix(){
        List<Member> members = memberService.getMemberByNamePrefix("user2");

        assertThat(members).isNotEmpty();
        assertThat(members).allMatch(m-> m.getName().startsWith("user2")); // user20~user29 포함
    }
}
