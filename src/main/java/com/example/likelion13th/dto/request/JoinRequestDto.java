package com.example.likelion13th.dto.request;

import com.example.likelion13th.domain.Member;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.likelion13th.enums.Role;

@Getter
public class JoinRequestDto {
    private String name;
    private String password;
    private Role role;

    public Member toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return Member.builder()
                .name(this.name)
                .password(bCryptPasswordEncoder.encode(this.password))
                .role(this.role)
                .build();
    }
}
