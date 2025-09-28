package com.example.likelion13th.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotBuyerException extends RuntimeException {
    public NotBuyerException() {
        super("주문은 Buyer 권한을 가진 멤버만 등록할 수 있습니다.");
    }
}
