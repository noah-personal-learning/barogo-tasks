package com.barogo.api.domain.delivery.service;

import com.barogo.api.domain.delivery.dto.request.DeliveryAddressRequestDto;
import com.barogo.api.domain.delivery.dto.request.DeliveryHistoryRequestDto;
import com.barogo.api.domain.delivery.dto.response.DeliveryAddressResponseDto;
import com.barogo.api.domain.delivery.dto.response.DeliveryHistoryResponseDto;
import com.barogo.api.domain.delivery.entity.Delivery;
import com.barogo.api.domain.delivery.exception.MaxBeforeDayException;
import com.barogo.api.domain.delivery.exception.NotFoundDeliveryException;
import com.barogo.api.domain.delivery.repository.DeliveryRepository;
import com.barogo.api.domain.order.entity.Order;
import com.barogo.api.domain.order.exception.NotFoundOrderException;
import com.barogo.api.domain.order.exception.NotUpdateAddressException;
import com.barogo.api.domain.order.repository.OrderRepository;
import com.barogo.api.domain.user.exception.NotFoundUserException;
import com.barogo.api.global.util.code.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    public List<DeliveryHistoryResponseDto> getHistory(DeliveryHistoryRequestDto requestDto) {

        if (requestDto.getBeforeDay() > 3) {
            throw new MaxBeforeDayException();
        }


        requestDto = DeliveryHistoryRequestDto.builder()
                .id(requestDto.getId())
                .beforeDay(requestDto.getBeforeDay())
                .build();

        Order order = orderRepository.findById(requestDto.getId()).orElseThrow(NotFoundOrderException::new);

        Delivery delivery = requestDto.toEntity();

        return deliveryRepository.getNdayDeliveryHistory(requestDto.getBeforeDay());
    }

    public DeliveryAddressResponseDto updateAddress(DeliveryAddressRequestDto requestDto) {

        Delivery delivery = deliveryRepository.findById(requestDto.getDeliveryId()).orElseThrow(NotFoundDeliveryException::new);
        String original_address = delivery.getAddress();

        // 배송 시작전에만 주소 변경 가능.
        if (DeliveryStatus.BEFORE_DELIVERY.equals(delivery.getStatus())) {
            delivery.updateAddress(requestDto.getAddress());
        } else {
            throw new NotUpdateAddressException();
        }

        return DeliveryAddressResponseDto.builder()
                .deliveryId(delivery.getId())
                .originalDeliveryAddress(original_address)
                .updateDeliveryAddress(delivery.getAddress())
                .build();
    }
}
