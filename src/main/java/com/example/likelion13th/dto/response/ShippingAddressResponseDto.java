package com.example.likelion13th.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.example.likelion13th.domain.value.ShippingAddress;

@Getter
@Builder
@AllArgsConstructor
public class ShippingAddressResponseDto {
    private String receiverName;
    private String phone;
    private String roadAddress;
    private String detailAddress;
    private String zipcode;

    public static ShippingAddressResponseDto fromEntity(ShippingAddress address) {
        return ShippingAddressResponseDto.builder()
                .receiverName(address.getReceiverName())
                .phone(address.getPhone())
                .roadAddress(address.getRoadAddress())
                .detailAddress(address.getDetailAddress())
                .zipcode(address.getZipcode())
                .build();
    }
}
