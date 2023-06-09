package com.barogo.api.domain.delivery.entity;

import com.barogo.api.domain.order.entity.Order;
import com.barogo.api.global.util.DateEntity;
import com.barogo.api.global.util.code.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "delivery")
public class Delivery extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private DeliveryStatus status;

    @Builder
    public Delivery(Order order, String address, DeliveryStatus status) {
        this.order = order;
        this.address = address;
        this.status = status;
    }

    public void updateAddress(String address) {this.address = address;}
}
