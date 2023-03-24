package com.barogo.api.domain.delivery.dto.request;

import com.barogo.api.domain.delivery.entity.Delivery;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryHistoryRequestDto {

    @JsonProperty("order_id")
    @NotNull
    private Long id;

    @JsonProperty("before_day")
    @NotNull
    private Integer beforeDay;

    @Builder
    public DeliveryHistoryRequestDto(Long id, Integer beforeDay) {
        this.id = id;
        this.beforeDay = beforeDay;
    }

    public Delivery toEntity() {
        return Delivery.builder().build();
    }
}
