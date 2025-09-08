package com.getinthere.ncs.user.application.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "emp_tb")
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String sign; // base64 image
    private String fullName;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Builder
    public Emp(Long id, String sign, String fullName, LocalDateTime createdAt) {
        this.id = id;
        this.sign = sign;
        this.fullName = fullName;
        this.createdAt = createdAt;
    }
}
