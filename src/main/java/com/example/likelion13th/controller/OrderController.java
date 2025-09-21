package com.example.likelion13th.controller;

import com.example.likelion13th.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.likelion13th.dto.response.OrderResponseDto;
import com.example.likelion13th.dto.request.OrderRequestDto;
import com.example.likelion13th.dto.request.ShippingAddressUpdateRequestDto;

import java.util.List;

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

    // 구매자별 주문 목록 조회 (주문 목록 조회)
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrderByBuyer(@RequestParam("buyerId") Long buyerId) {
        return ResponseEntity.ok(orderService.getOrdersByBuyer(buyerId));
    }

    // 단건 주문 조회 (주문 상세 조회)
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderDetail(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.getOrderDetail(orderId));
    }

    // 3) 주문 수정: 배송지 변경
    @PatchMapping("/{orderId}/shipping")
    public ResponseEntity<OrderResponseDto> updateShipping(
            @PathVariable("orderId") Long orderId,
            @RequestBody ShippingAddressUpdateRequestDto dto
    ) {
        return ResponseEntity.ok(orderService.updateShipping(orderId, dto));
    }

    // 4) 주문 삭제: soft delete
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }



}
