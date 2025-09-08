package com.getinthere.ncs.course.application.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.getinthere.ncs.course.application.domain.enums.TeacherType;
import com.getinthere.ncs.user.application.domain.Teacher;

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
@Table(name = "teacher_in_course_tb")
public class TeacherInCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private TeacherType teacherType; // 메인강사, 보조강사

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public TeacherInCourse(Long id, Course course, Teacher teacher, TeacherType teacherType, LocalDateTime createdAt) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.teacherType = teacherType;
        this.createdAt = createdAt;
    }

}
