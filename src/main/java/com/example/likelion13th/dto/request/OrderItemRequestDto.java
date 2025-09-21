package com.example.likelion13th.dto.request;

import com.example.likelion13th.domain.Mapping.ProductOrders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.likelion13th.domain.Product;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDto {
    private Long productId; // 주문할 상품 Id
    private Integer quantity;

    public ProductOrders toEntity(Product product){
        return ProductOrders.builder()
                .product(product)
                .quantity(this.quantity)
                .build();
    }
}
