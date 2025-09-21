package com.example.likelion13th.dto.request;

import com.example.likelion13th.domain.Orders;
import com.example.likelion13th.domain.Member;
import com.example.likelion13th.enums.DeliverStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long memberId; // 구매자 ID
    private ShippingAddressDto shipping; // 배송정보
    private List<OrderItemRequestDto> items; // 주문 상품 목록

    public Orders toEntity(Member buyer) {
        return Orders.builder()
                .deliverStatus(DeliverStatus.PREPARATION)
                .shippingAddress(this.shipping.toEntity())
                .buyer(buyer)
                .build();
    }
}
