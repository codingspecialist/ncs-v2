package com.getinthere.ncs.user.application.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "teacher_tb")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Lob
    private String sign; // base64 image
    private Boolean isVerified; // 승인여부 (false)

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Builder
    public Teacher(Long id, String fullName, String sign, Boolean isVerified, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.sign = sign;
        this.isVerified = isVerified;
        this.createdAt = createdAt;
    }

}
