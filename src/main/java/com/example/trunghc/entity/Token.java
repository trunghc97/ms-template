package com.example.trunghc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tokens")
@Getter
@Setter
public class Token extends BaseEntity {

    @Column(length = 1000)
    private String token;

    private Date tokenExpDate;

    private Long userId;

    @PrePersist
    private void prePersistFunction() {
        this.setCreatedBy("System");
        this.setCreatedAt(new Date());
    }

    @PreUpdate
    private void preUpdateFunction() {
        this.setUpdatedBy("System");
        this.setUpdatedAt(new Date());
    }

}
