package com.example.likelion13th.domain;

import com.example.likelion13th.domain.Mapping.ProductOrders;
import com.example.likelion13th.domain.value.ShippingAddress;
import com.example.likelion13th.enums.DeliverStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliverStatus deliverStatus; // 배송상태

    @ManyToOne
    @JoinColumn(name ="buyer_id")
    private Member buyer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductOrders> productOrders = new ArrayList<>();

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    private Coupon coupon;

    @Embedded
    private ShippingAddress shippingAddress;

    @Column(nullable = false)
    @Builder.Default
    private boolean deleted = false; // soft delete 플래그

    public boolean canEditShipping() { // 배송지 수정 가능?
        return this.deliverStatus == DeliverStatus.PREPARATION;
    }

    public boolean canSoftDelete() {   // 삭제(소프트) 가능?
        return this.deliverStatus == DeliverStatus.COMPLETED;
    }

    public void updateShipping(ShippingAddress newAddress) {
        this.shippingAddress = newAddress;
    }

    public void softDelete() {
        this.deleted = true;
    }

    public void addLine(ProductOrders line){
        productOrders.add(line);
        line.setOrders(this); // FK 세팅(연관관계 주인)
    }
}

