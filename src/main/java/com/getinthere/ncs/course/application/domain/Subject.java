package com.getinthere.ncs.course.application.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.getinthere.ncs.course.application.domain.enums.LearningWay;
import com.getinthere.ncs.course.application.domain.enums.NcsType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "subject_tb")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // 코드는 고유해야 함
    private String code; // 교과목 ID (능력단위코드)
    private String title; // 교과목명 (능력단위명)
    private String purpose; // 교과목 목표
    private Integer totalTime; // 교과목 시간
    private Integer no; // 교과목 번호

    @Enumerated(EnumType.STRING)
    private NcsType ncsType; // NCS, 비NCS
    private Integer gradeLevel; // 교과목 수준
    @Enumerated(EnumType.STRING)
    private LearningWay learningWay; // 교수 학습 방법
    private Double scorePolicy; // 결시자/재평가자 감점 비율 (0.9, 0.8)

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private TeacherInCourse teacherInCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<SubjectElement> elements = new ArrayList<>();

    @Builder
    public Subject(Long id, String code, String title, String purpose, Integer totalTime, Integer no, NcsType ncsType,
            Integer gradeLevel, LearningWay learningWay, Double scorePolicy, LocalDateTime createdAt,
            LocalDate startDate, LocalDate endDate, TeacherInCourse teacherInCourse, Course course) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.purpose = purpose;
        this.totalTime = totalTime;
        this.no = no;
        this.ncsType = ncsType;
        this.gradeLevel = gradeLevel;
        this.learningWay = learningWay;
        this.scorePolicy = scorePolicy;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacherInCourse = teacherInCourse;
        this.course = course;
    }

}
