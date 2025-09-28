package com.example.likelion13th.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.likelion13th.domain.value.ShippingAddress;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddressDto {
    private String receiverName;
    private String phone;
    private String roadAddress;
    private String detailAddress;
    private String zipcode;

    public ShippingAddress toEntity() {
        return ShippingAddress.builder()
                .receiverName(this.receiverName)
                .phone(this.phone)
                .roadAddress(this.roadAddress)
                .detailAddress(this.detailAddress)
                .zipcode(this.zipcode)
                .build();
    }
}
