package com.barogo.api.domain.order.dto.response;

import com.barogo.api.global.util.code.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderResponseDto {

    @JsonProperty("order_id")
    private Long orderId;
    @JsonProperty("order_date")
    private LocalDateTime orderDate;
    @JsonProperty("address")
    private String address;
    @JsonProperty("status")
    private OrderStatus status;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("fee")
    private BigDecimal fee;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_count")
    private Integer productCount;
    @JsonProperty("product_unit_price")
    private BigDecimal productUnitPrice;

    @Builder
    public OrderResponseDto(Long orderId, LocalDateTime orderDate, String address, OrderStatus status,
                            BigDecimal amount, BigDecimal fee,
                            String productName, Integer productCount, BigDecimal productUnitPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.address = address;
        this.status = status;
        this.amount = amount;
        this.fee = fee;
        this.productName = productName;
        this.productCount = productCount;
        this.productUnitPrice = productUnitPrice;
    }
}
