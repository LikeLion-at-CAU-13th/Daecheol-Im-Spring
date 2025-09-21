package com.example.likelion13th.dto.response;

import com.example.likelion13th.domain.Orders;
import com.example.likelion13th.dto.response.OrderItemResponseDto;
import com.example.likelion13th.dto.response.ShippingAddressResponseDto;
import com.example.likelion13th.enums.DeliverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private DeliverStatus deliverStatus;
    private Long buyerId;
    private ShippingAddressResponseDto shipping;
    private List<OrderItemResponseDto> items;

    public static OrderResponseDto fromEntity(Orders orders) {
        return OrderResponseDto.builder()
                .id(orders.getId())
                .deliverStatus(orders.getDeliverStatus())
                .buyerId(orders.getBuyer().getId())
                .shipping(ShippingAddressResponseDto.fromEntity(orders.getShippingAddress()))
                .items(
                        orders.getProductOrders().stream()
                                .map(OrderItemResponseDto::fromEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
