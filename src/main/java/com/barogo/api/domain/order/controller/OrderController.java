package com.barogo.api.domain.order.controller;

import com.barogo.api.domain.order.dto.request.OrderAddressRequestDto;
import com.barogo.api.domain.order.dto.request.OrderReceiptRequestDto;
import com.barogo.api.domain.order.dto.request.OrderRequestDto;
import com.barogo.api.domain.order.dto.response.OrderAddressResponseDto;
import com.barogo.api.domain.order.dto.response.OrderReceiptResponseDto;
import com.barogo.api.domain.order.dto.response.OrderResponseDto;
import com.barogo.api.domain.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Object> orderRequest(@RequestBody @Validated OrderRequestDto requestDto) {

        OrderResponseDto responseDto = orderService.add(requestDto);

        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }

    @PostMapping("/order/receipt")
    public ResponseEntity<Object> orderReceipt(@RequestBody @Validated OrderReceiptRequestDto requestDto) {

        OrderReceiptResponseDto responseDto = orderService.updateStatus(requestDto);

        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }

    @PostMapping("/order/address")
    public ResponseEntity<Object> orderUpdateAddress(@RequestBody @Validated OrderAddressRequestDto requestDto) {

        OrderAddressResponseDto responseDto = orderService.updateAddress(requestDto);

        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }

}
