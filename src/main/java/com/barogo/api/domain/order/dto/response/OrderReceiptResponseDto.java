package com.barogo.api.domain.order.dto.response;

import com.barogo.api.domain.delivery.dto.response.DeliveryStatusResponeDto;
import com.barogo.api.global.util.code.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderReceiptResponseDto {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("order_status")
    private OrderStatus status;

    @JsonProperty("delivery")
    private DeliveryStatusResponeDto deliveryStatusResponeDto;

    @Builder
    public OrderReceiptResponseDto(Long orderId, OrderStatus status, DeliveryStatusResponeDto deliveryStatusResponeDto) {
        this.orderId = orderId;
        this.status = status;
        this.deliveryStatusResponeDto = deliveryStatusResponeDto;
    }
}
