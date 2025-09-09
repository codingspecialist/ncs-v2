package com.getinthere.ncs.user.application.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.getinthere.ncs.user.web.dto.AuthRequest;

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
public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(unique = true, length = 20)
        private String username;
        private String password;
        private String email;
        @Enumerated(EnumType.STRING)
        private String roles; // STUDENT, TEACHER, EMP

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
        private Student student;

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
        private Teacher teacher;

        @CreationTimestamp
        private LocalDateTime createdAt;

        @Builder
        public User(Long id, String username, String password, String email, String roles,
                        Student student, Teacher teacher, LocalDateTime createdAt) {
                this.id = id;
                this.username = username;
                this.password = password;
                this.email = email;
                this.roles = roles;
                this.student = student;
                this.teacher = teacher;
                this.createdAt = createdAt;
        }

        public static User createStudent(AuthRequest.StudentJoinDTO reqDTO) {
                Student student = Student.builder()
                                .birthday(reqDTO.birthday())
                                .fullName(reqDTO.fullName())
                                .build();

                User user = User.builder()
                                .username(reqDTO.username())
                                .password(reqDTO.password())
                                .email(reqDTO.email())
                                .roles("STUDENT") // 역할 명시
                                .student(student) // User가 주인이므로 Student 객체 설정
                                .build();

                return user;
        }

        public static User createTeacher(AuthRequest.TeacherJoinDTO reqDTO) {
                Teacher teacher = Teacher.builder()
                                .fullName(reqDTO.fullName())
                                .sign(reqDTO.sign())
                                .build();

                User user = User.builder()
                                .username(reqDTO.username())
                                .password(reqDTO.password())
                                .email(reqDTO.email())
                                .roles("TEACHER") // 역할 명시
                                .teacher(teacher) // User가 주인이므로 Teacher 객체 설정
                                .build();

                return user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection<GrantedAuthority> authorities = new ArrayList<>();

                String[] roleList = roles.split(",");

                for (String role : roleList) {
                        authorities.add(() -> "ROLE_" + role);
                }
                return authorities;
        }
}