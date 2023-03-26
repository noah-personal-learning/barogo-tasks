package com.barogo.api.domain.order.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderReceiptRequestDto {

    @JsonProperty("order_id")
    private Long orderId;

    @Builder
    public OrderReceiptRequestDto(Long orderId) {
        this.orderId = orderId;
    }
}
