package com.example.likelion13th.domain.value;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access =AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ShippingAddress {
    private String receiverName; // 수령인
    private String phone; // 전화번호
    private String roadAddress; // 도로명주소
    private String detailAddress; // 상세주소
    private String zipcode; // 우편번호
}
