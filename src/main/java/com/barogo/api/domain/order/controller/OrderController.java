package com.barogo.api.domain.order.controller;

import com.barogo.api.domain.order.dto.request.OrderRequestDto;
import com.barogo.api.domain.order.dto.response.OrderResponseDto;
import com.barogo.api.domain.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public ResponseEntity<Object> orderRequest(@RequestBody @Validated OrderRequestDto requestDto) {

        OrderResponseDto responseDto = orderService.orderRequest(requestDto);

        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }

    @PatchMapping("/order/receipt")
    public ResponseEntity<Object> orderReceipt(@RequestBody @Validated OrderRequestDto requestDto) {

        OrderResponseDto responseDto = orderService.orderRequest(requestDto);

        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }

}
