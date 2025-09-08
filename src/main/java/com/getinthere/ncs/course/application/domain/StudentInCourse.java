package com.getinthere.ncs.course.application.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.getinthere.ncs.user.application.domain.Student;
import com.getinthere.ncs.user.application.domain.enums.StudentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "student_in_course_tb")
public class StudentInCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    private LocalDate dropOutDate; // 중탈 날짜
    private String dropOutReason; // 중탈 이유
    private String comment; // 학생 모든 교과목에 대한 총평
    private Integer gradeLevel; // 학생 모든 교과목에 대한 수준 1,2,3,4,5
    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus; // 취업, 중도탈락, 재학중

    @Column(unique = true)
    private String authCode; // 학생 인증 코드
    private Boolean isVerified; // 학생 인증 여부

    public void setVerified() {
        this.isVerified = true;
        this.authCode = null;
    }

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public StudentInCourse(Long id, Course course, Student student, LocalDate dropOutDate, String dropOutReason,
            String comment, Integer gradeLevel, StudentStatus studentStatus, String authCode, Boolean isVerified,
            LocalDateTime createdAt) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.dropOutDate = dropOutDate;
        this.dropOutReason = dropOutReason;
        this.comment = comment;
        this.gradeLevel = gradeLevel;
        this.studentStatus = studentStatus;
        this.authCode = authCode;
        this.isVerified = isVerified;
        this.createdAt = createdAt;
    }

}
