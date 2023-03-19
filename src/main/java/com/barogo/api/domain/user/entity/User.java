package com.barogo.api.domain.user.entity;

import com.barogo.api.domain.order.entity.Order;
import com.barogo.api.global.util.DateEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "reg_ip")
    private String regIp;

    // @OneToMany(mappedBy = "user")
    // private List<Order> orders = new ArrayList<>();

}
