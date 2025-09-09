package com.getinthere.ncs.user.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getinthere.ncs.user.application.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
