package com.barogo.api.domain.delivery.dto.response;

import com.barogo.api.global.util.code.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryStatusResponeDto {

    @JsonProperty("delivery_id")
    private Long deliveryId;
    @JsonProperty("delivery_status")
    private DeliveryStatus deliveryStatus;

    @Builder
    public DeliveryStatusResponeDto(Long deliveryId, DeliveryStatus deliveryStatus) {
        this.deliveryId = deliveryId;
        this.deliveryStatus = deliveryStatus;
    }
}
