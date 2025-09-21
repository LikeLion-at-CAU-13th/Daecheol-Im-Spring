package com.example.likelion13th.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.example.likelion13th.domain.Mapping.ProductOrders;

@Getter
@Builder
@AllArgsConstructor
public class OrderItemResponseDto {
    private Long productId;
    private String productName;
    private Integer quantity;

    public static OrderItemResponseDto fromEntity(ProductOrders po) {
        return OrderItemResponseDto.builder()
                .productId(po.getProduct().getId())
                .productName(po.getProduct().getName())
                .quantity(po.getQuantity())
                .build();
    }
}
