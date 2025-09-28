package com.example.likelion13th.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 컨트롤러에서 자동으로 404 반환
public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("존재하지 않는 멤버입니다. id=" + id);
    }
}