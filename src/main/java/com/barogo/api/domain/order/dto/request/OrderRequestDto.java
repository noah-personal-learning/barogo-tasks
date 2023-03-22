package com.barogo.api.domain.order.dto.request;

import com.barogo.api.domain.order.entity.Order;
import com.barogo.api.global.util.code.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderRequestDto {

    @JsonProperty("address")
    private String address;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_count")
    private BigDecimal productCount;
    @JsonProperty("product_unit_price")
    private BigDecimal productUnitPrice;

    private BigDecimal amount;
    private BigDecimal fee;

    private OrderStatus status;
    private LocalDateTime orderDate;


    @Builder
    public OrderRequestDto(String address,
                           String productName, BigDecimal productCount, BigDecimal productUnitPrice) {
        this.orderDate = LocalDateTime.now();
        this.address = address;
        this.status = OrderStatus.ORDER_REQUEST;
        this.amount = productUnitPrice.multiply(productCount);
        this.fee = this.amount.multiply(new BigDecimal("0.01")).setScale(2, RoundingMode.HALF_UP);
        this.productName = productName;
        this.productCount = productCount;
        this.productUnitPrice = productUnitPrice;
    }

    public Order toEntity() {
        return Order.builder()
                .orderDate(this.orderDate)
                .address(this.address)
                .status(this.status)
                .amount(this.amount)
                .fee(this.fee)
                .productName(this.productName)
                .productCount(this.productCount)
                .productUnitPrice(this.productUnitPrice)
                .build();
    }
}
