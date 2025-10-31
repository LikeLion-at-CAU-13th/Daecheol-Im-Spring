package com.example.likelion13th.controller;

import com.example.likelion13th.domain.Member;
import com.example.likelion13th.config.PrincipalHandler;
import com.example.likelion13th.dto.request.JoinRequestDto;
import com.example.likelion13th.dto.response.TokenResponseDto;
import com.example.likelion13th.jwt.JwtTokenProvider;
import com.example.likelion13th.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/join")
    public void join(@RequestBody JoinRequestDto joinRequestDto) {
        memberService.join(joinRequestDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody JoinRequestDto joinRequestDto) {
        Member member = memberService.login(joinRequestDto);
        return TokenResponseDto.of(jwtTokenProvider.generateAccessToken(member.getName()), jwtTokenProvider.generateRefreshToken(member.getName()));
    }

    @GetMapping("/my")
    public ResponseEntity<String> getMyName() {
        String name = PrincipalHandler.getUsernameFromPrincipal();
        return ResponseEntity.ok(name);
    }
}
