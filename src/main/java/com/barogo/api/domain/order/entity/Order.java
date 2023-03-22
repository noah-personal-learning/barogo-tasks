package com.barogo.api.domain.order.entity;

import com.barogo.api.global.util.DateEntity;
import com.barogo.api.global.util.code.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity(name = "orders")
public class Order extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "complete_date")
    private LocalDateTime completeDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "fee")
    private BigDecimal fee;

    // todo. Product Entity 필요
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_count")
    private BigDecimal productCount;

    @Column(name = "product_unit_price")
    private BigDecimal productUnitPrice;

    @Builder
    public Order(LocalDateTime orderDate, String address, OrderStatus status,
                 LocalDateTime completeDate, BigDecimal amount, BigDecimal fee,
                 String productName, BigDecimal productCount, BigDecimal productUnitPrice) {
        this.orderDate = orderDate;
        this.address = address;
        this.status = status;
        this.completeDate = completeDate;
        this.amount = amount;
        this.fee = fee;
        this.productName = productName;
        this.productCount = productCount;
        this.productUnitPrice = productUnitPrice;
    }
}
