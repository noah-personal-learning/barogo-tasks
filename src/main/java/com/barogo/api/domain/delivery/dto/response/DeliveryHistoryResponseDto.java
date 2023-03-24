package com.barogo.api.domain.delivery.dto.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryHistoryResponseDto {

    private Long id;

    @Builder
    public DeliveryHistoryResponseDto(Long id) {
        this.id = id;
    }
}
