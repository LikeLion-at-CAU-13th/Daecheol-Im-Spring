package com.example.likelion13th.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN) // 403 반환
public class NotSellerException extends RuntimeException {
    public NotSellerException() {
        super("상품은 SELLER 권한을 가진 멤버만 등록할 수 있습니다.");
    }
}