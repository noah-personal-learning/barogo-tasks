package com.barogo.api.domain.delivery.controller;

import com.barogo.api.domain.delivery.dto.request.DeliveryAddressRequestDto;
import com.barogo.api.domain.delivery.dto.response.DeliveryAddressResponseDto;
import com.barogo.api.domain.delivery.dto.request.DeliveryHistoryRequestDto;
import com.barogo.api.domain.delivery.dto.response.DeliveryHistoryResponseDto;
import com.barogo.api.domain.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/delivery/history")
    public ResponseEntity<Object> requestDeliveryHistory(
            @RequestBody @Validated DeliveryHistoryRequestDto requestDto) {

        List<DeliveryHistoryResponseDto> responseDto = deliveryService.getHistory(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @PostMapping("/delivery/address")
    public ResponseEntity<Object> orderUpdateAddress(@RequestBody @Validated DeliveryAddressRequestDto requestDto) {

        DeliveryAddressResponseDto responseDto = deliveryService.updateAddress(requestDto);

        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }
}
