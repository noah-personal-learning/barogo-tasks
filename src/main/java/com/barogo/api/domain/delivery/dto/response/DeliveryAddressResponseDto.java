package com.barogo.api.domain.delivery.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddressResponseDto {

    @JsonProperty("delivery_id")
    private Long deliveryId;

    @JsonProperty("original_delivery_address")
    private String originalDeliveryAddress;

    @JsonProperty("update_delivery_address")
    private String updateDeliveryAddress;

    @Builder
    public DeliveryAddressResponseDto(Long deliveryId, String originalDeliveryAddress, String updateDeliveryAddress) {
        this.deliveryId = deliveryId;
        this.originalDeliveryAddress = originalDeliveryAddress;
        this.updateDeliveryAddress = updateDeliveryAddress;
    }
}
