package com.barogo.api.domain.delivery.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @OneToOne
    @Column(name = "order_id")
    private String order_id;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private String status;

}
