package com.barogo.api.domain.order.entity;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private String status;
    @Column(name = "complete_date")
    private LocalDateTime completeDate;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "fee")
    private BigDecimal fee;

}
