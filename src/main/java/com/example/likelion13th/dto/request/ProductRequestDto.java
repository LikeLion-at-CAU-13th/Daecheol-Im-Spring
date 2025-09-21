package com.example.likelion13th.dto.request;

import com.example.likelion13th.domain.Member;
import com.example.likelion13th.domain.Product;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String name;
    private Integer price;
    private Integer stock;
    private String description;
    private Long memberId; // 판매자인지 확인하기 위함

    public Product toEntity(Member seller) {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .stock(this.stock)
                .description(this.description)
                .seller(seller)
                .build();
    }
}