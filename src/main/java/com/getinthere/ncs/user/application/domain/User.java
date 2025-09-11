package com.getinthere.ncs.user.application.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
        private String roles; // STUDENT, TEACHER

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