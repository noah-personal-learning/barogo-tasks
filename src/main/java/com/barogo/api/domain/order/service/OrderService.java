package com.barogo.api.domain.order.service;

import com.barogo.api.domain.order.dto.request.OrderRequestDto;
import com.barogo.api.domain.order.dto.response.OrderResponseDto;
import com.barogo.api.domain.order.entity.Order;
import com.barogo.api.domain.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponseDto orderRequest(OrderRequestDto requestDto) {

        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .address(requestDto.getAddress())
                .productName(requestDto.getProductName())
                .productCount(requestDto.getProductCount())
                .productUnitPrice(requestDto.getProductUnitPrice())
                .build();

        Order order = orderRequestDto.toEntity();
        order = orderRepository.save(order);

        return OrderResponseDto.builder()
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

}
