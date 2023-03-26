package com.barogo.api.domain.order.service;

import com.barogo.api.domain.delivery.dto.response.DeliveryStatusResponeDto;
import com.barogo.api.domain.delivery.entity.Delivery;
import com.barogo.api.domain.delivery.repository.DeliveryRepository;
import com.barogo.api.domain.order.dto.request.OrderAddressRequestDto;
import com.barogo.api.domain.order.dto.request.OrderRequestDto;
import com.barogo.api.domain.order.dto.request.OrderReceiptRequestDto;
import com.barogo.api.domain.order.dto.response.OrderAddressResponseDto;
import com.barogo.api.domain.order.dto.response.OrderResponseDto;
import com.barogo.api.domain.order.dto.response.OrderReceiptResponseDto;
import com.barogo.api.domain.order.entity.Order;
import com.barogo.api.domain.order.exception.NotFoundOrderException;
import com.barogo.api.domain.order.exception.NotUpdateAddressException;
import com.barogo.api.domain.order.repository.OrderRepository;
import com.barogo.api.global.util.code.DeliveryStatus;
import com.barogo.api.global.util.code.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;

    public OrderResponseDto add(OrderRequestDto requestDto) {

        requestDto = OrderRequestDto.builder()
                .address(requestDto.getAddress())
                .productName(requestDto.getProductName())
                .productCount(requestDto.getProductCount())
                .productUnitPrice(requestDto.getProductUnitPrice())
                .build();

        Order order = requestDto.toEntity();
        order = orderRepository.save(order);

        return OrderResponseDto.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .address(order.getAddress())
                .status(order.getStatus())
                .amount(order.getAmount())
                .fee(order.getFee())
                .productName(order.getProductName())
                .productCount(order.getProductCount())
                .productUnitPrice(order.getProductUnitPrice())
                .build();

    }

    public OrderReceiptResponseDto updateStatus(OrderReceiptRequestDto requestDto) {

        log.info("requestDto = {}", requestDto);

        Order order = orderRepository.findById(requestDto.getOrderId()).orElseThrow(NotFoundOrderException::new);
        order.updateStatus(OrderStatus.ORDER_ACCEPT);

        Delivery delivery = Delivery.builder()
                .order(order)
                .address(order.getAddress())
                .status(DeliveryStatus.BEFORE_DELIVERY)
                .build();

        delivery = deliveryRepository.save(delivery);

        DeliveryStatusResponeDto deliveryResponse = DeliveryStatusResponeDto.builder()
                .deliveryId(delivery.getId())
                .deliveryStatus(delivery.getStatus())
                .build();

        return OrderReceiptResponseDto.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .deliveryStatusResponeDto(deliveryResponse)
                .build();
    }

    public OrderAddressResponseDto updateAddress(OrderAddressRequestDto requestDto) {

        Order order = orderRepository.findById(requestDto.getOrderId()).orElseThrow(NotFoundOrderException::new);
        String originalAddress = order.getAddress();
        // 주문 접수전 변경 가능
        if (OrderStatus.ORDER_REQUEST.equals(order.getStatus())) {
            order.updateAddress(requestDto.getAddress());
        } else {
            throw new NotUpdateAddressException();
        }

        return OrderAddressResponseDto.builder()
                .orderId(order.getId())
                .orgAddress(originalAddress)
                .targetAddress(order.getAddress())
                .build();

    }
}
