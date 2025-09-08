package com.getinthere.ncs.course.application.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "subject_element_tb", uniqueConstraints = {
        @UniqueConstraint(name = "uk_subject_no", columnNames = { "subject_id", "no" })
})
public class SubjectElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer no; // 교과목에서의 순번
    private String title; // 교과목 요소 이름
    private String criterion; // 교과목 요소 평가기준

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setCourseSubject(Subject subject) {
        this.subject = subject;
    }

    @Builder
    public SubjectElement(Long id, Integer no, String title, String criterion, Subject subject,
            LocalDateTime createdAt) {
        this.id = id;
        this.no = no;
        this.title = title;
        this.criterion = criterion;
        this.subject = subject;
        this.createdAt = createdAt;
    }
}
