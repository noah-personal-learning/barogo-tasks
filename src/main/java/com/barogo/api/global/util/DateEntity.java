package com.barogo.api.global.util;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
public class DateEntity {

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "reg_worker")
    private String regWorker;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "mod_worker")
    private String modWorker;

}
