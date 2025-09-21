package com.example.likelion13th.dto.response;

import com.example.likelion13th.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;
    private String description;

    public static ProductResponseDto fromEntity(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .build();
    }
}
