package com.example.likelion13th.controller;

import com.example.likelion13th.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.likelion13th.dto.response.OrderResponseDto;
import com.example.likelion13th.dto.request.OrderRequestDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderRequestDto dto){
        return ResponseEntity.ok(orderService.createOrder(dto));
    }

}
