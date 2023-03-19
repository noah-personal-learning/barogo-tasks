package com.barogo.api.domain.user.entity;

import com.barogo.api.global.util.DateEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User extends DateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

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


    @Builder
    public User(String userId, String password, String name, String address, String regIp) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.regIp = regIp;

    }
}
