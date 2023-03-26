package com.barogo.api.domain.order.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAddressResponseDto {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("original_address")
    private String orgAddress;

    @JsonProperty("target_address")
    private String targetAddress;

    @Builder
    public OrderAddressResponseDto(Long orderId, String orgAddress, String targetAddress) {
        this.orderId = orderId;
        this.orgAddress = orgAddress;
        this.targetAddress = targetAddress;
    }
}
