package com.barogo.api.domain.delivery.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddressRequestDto {

    @JsonProperty("delivery_id")
    private Long deliveryId;

    @JsonProperty("address")
    private String address;

    @Builder
    public DeliveryAddressRequestDto(Long deliveryId, String address) {
        this.deliveryId = deliveryId;
        this.address = address;
    }
}
