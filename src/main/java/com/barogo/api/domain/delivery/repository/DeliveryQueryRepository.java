package com.barogo.api.domain.delivery.repository;

import com.barogo.api.domain.delivery.dto.request.DeliveryHistoryRequestDto;
import com.barogo.api.domain.delivery.dto.response.DeliveryHistoryResponseDto;

import java.util.List;

public interface DeliveryQueryRepository {

    List<DeliveryHistoryResponseDto> getNdayDeliveryHistory(Integer date);

}
