package com.getinthere.ncs.user.application.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.getinthere.ncs.user.application.domain.enums.StudentStatus;
import com.getinthere.ncs.user.web.dto.UserRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private String roles; // 학생, 강사, 직원

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Teacher teacher;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Emp emp;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public User(String username, String password, String email, String roles, Student student, Teacher teacher,
            Emp emp) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.student = student;
        this.teacher = teacher;
        this.emp = emp;
    }

    public static User createStudent(UserRequest.StudentJoin request) {
        Student student = Student.builder()
                .birthday(request.birthday())
                .fullName(request.fullName())
                .build();

        User user = User.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .roles("STUDENT") // 역할 명시
                .student(student) // User가 주인이므로 Student 객체 설정
                .build();

        return user;
    }

    public static User createTeacher(UserRequest.TeacherJoin request) {
        Teacher teacher = Teacher.builder()
                .fullName(request.fullName())
                .sign(request.sign())
                .build();

        User user = User.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .roles("TEACHER") // 역할 명시
                .teacher(teacher) // User가 주인이므로 Teacher 객체 설정
                .build();

        return user;
    }

    public static User createEmp(UserRequest.EmpJoin request) {
        Emp emp = Emp.builder()
                .fullName(request.fullName())
                .sign(request.sign())
                .build();

        User user = User.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .roles("EMP") // 역할 명시
                .emp(emp) // User가 주인이므로 Emp 객체 설정
                .build();

        return user;
    }
}