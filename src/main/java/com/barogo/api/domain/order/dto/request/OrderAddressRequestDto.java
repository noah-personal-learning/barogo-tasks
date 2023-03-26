package com.barogo.api.domain.order.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAddressRequestDto {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("address")
    private String address;

    @Builder
    public OrderAddressRequestDto(Long orderId, String address) {
        this.orderId = orderId;
        this.address = address;
    }
}
