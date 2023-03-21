package com.barogo.api.domain.delivery.controller;

import com.barogo.api.domain.delivery.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;
}
