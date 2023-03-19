package com.barogo.api.global.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class DateEntity {

    @CreatedDate
    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @CreatedBy
    @Column(name = "reg_worker")
    private String regWorker;

    @LastModifiedDate
    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @LastModifiedBy
    @Column(name = "mod_worker")
    private String modWorker;

}
