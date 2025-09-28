package com.example.likelion13th.domain.Mapping;

import com.example.likelion13th.domain.Orders;
import com.example.likelion13th.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    private Integer quantity; // 구매 수량

    public void setOrders(Orders orders){ this.orders = orders; }
}

