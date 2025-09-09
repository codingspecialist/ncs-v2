package com.getinthere.ncs.user.application.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "student_tb")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String birthday; // 생년월일 (800825)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Student(Long id, String fullName, String birthday, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.createdAt = createdAt;
    }
}
