package com.getinthere.ncs.course.application.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.getinthere.ncs.course.application.domain.enums.CourseStatus;
import com.getinthere.ncs.course.application.domain.enums.TeacherType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "course_tb")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK
    private String code; // 과정ID (국가에서 쓰는 것) 한과정이 5번 실행되면, 과정아이디 같다.
    private String title; // 과정명
    private Integer level; // 훈련수준
    private Integer round; // 1회차, 2회차
    private String purpose; // 과정목표
    private Integer totalTime; // 과정시간
    private Integer totalDay; // 과정일수
    private LocalDate startDate; // 년월일
    private LocalDate endDate; // 년월일
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus; // 과정진행전, 과정진행중, 과정종료 (기본값은 과정진행전이다 - 숫자로는 0번)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeacherInCourse> teachersInCourses = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentInCourse> studentsInCourses = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects = new ArrayList<>();

    public String getMainTeacherName() {
        return teachersInCourses.stream()
                .filter(tic -> tic.getTeacherType() == TeacherType.MAIN)
                .map(tic -> tic.getTeacher().getFullName())
                .findFirst()
                .orElse(null);
    }

}
