package com.barogo.api.domain.delivery.controller;

import com.barogo.api.domain.delivery.dto.request.DeliveryHistoryRequestDto;
import com.barogo.api.domain.delivery.dto.response.DeliveryHistoryResponseDto;
import com.barogo.api.domain.delivery.service.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
}
