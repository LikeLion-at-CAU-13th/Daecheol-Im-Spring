package com.example.likelion13th.dto.request;

import com.example.likelion13th.domain.value.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddressUpdateRequestDto {
    private String receiverName;
    private String phone;
    private String roadAddress;
    private String detailAddress;
    private String zipcode;

    public ShippingAddress toEmbeddable() {
        return new ShippingAddress(receiverName, phone, roadAddress, detailAddress, zipcode);
    }
}