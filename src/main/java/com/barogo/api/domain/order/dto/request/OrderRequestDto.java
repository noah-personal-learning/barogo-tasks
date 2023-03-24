package com.barogo.api.domain.order.dto.request;

import com.barogo.api.domain.order.entity.Order;
import com.barogo.api.global.util.code.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderRequestDto {

    @JsonProperty("address")
    @NotNull(message = "address is required")
    private String address;

    @JsonProperty("product_name")
    @NotNull(message = "product_name is required")
    private String productName;

    @JsonProperty("product_count")
    @NotNull(message = "product_count is required")
    private Integer productCount;

    @JsonProperty("product_unit_price")
    @NotNull(message = "product_unit_price is required")
    private BigDecimal productUnitPrice;

    private BigDecimal amount;
    private BigDecimal fee;

    private OrderStatus status;
    private LocalDateTime orderDate;


    @Builder
    public OrderRequestDto(String address,
                           String productName, Integer productCount, BigDecimal productUnitPrice) {
        this.orderDate = LocalDateTime.now();
        this.address = address;
        this.status = OrderStatus.ORDER_REQUEST;
        this.amount = productUnitPrice.multiply(BigDecimal.valueOf(productCount));
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
