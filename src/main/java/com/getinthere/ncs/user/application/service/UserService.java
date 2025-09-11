package com.getinthere.ncs.user.application.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getinthere.ncs._core.auth.JwtUtil;
import com.getinthere.ncs._core.errors.ex.Exception400;
import com.getinthere.ncs._core.errors.ex.Exception401;
import com.getinthere.ncs._core.errors.ex.Exception404;
import com.getinthere.ncs.user.application.domain.User;
import com.getinthere.ncs.user.application.repository.UserRepository;
import com.getinthere.ncs.user.web.dto.AuthRequest;
import com.getinthere.ncs.user.web.dto.AuthResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public AuthResponse.DTO 강사_회원가입(AuthRequest.TeacherJoinDTO reqDTO) {
        // 1. 유저네임 중복검사
        if (userRepository.findByUsername(reqDTO.username()).isPresent())
            throw new Exception400("이미 존재하는 유저네임입니다");

        // 2. 회원가입 (User, Teacher)
        String encPassword = bCryptPasswordEncoder.encode(reqDTO.password());
        User savedUser = userRepository.save(reqDTO.toEntity(encPassword));
        return new AuthResponse.DTO(savedUser);
    }

    // 인증코드로 인증필요
    @Transactional
    public AuthResponse.DTO 학생_회원가입(AuthRequest.StudentJoinDTO reqDTO) {
        // 1. 유저네임 중복검사
        if (userRepository.findByUsername(reqDTO.username()).isPresent())
            throw new Exception400("이미 존재하는 유저네임입니다");

        // 2. 회원가입 (User, Student)
        String encPassword = bCryptPasswordEncoder.encode(reqDTO.password());
        User savedUser = userRepository.save(reqDTO.toEntity(encPassword));
        return new AuthResponse.DTO(savedUser);
    }

    public String 로그인(AuthRequest.LoginDTO reqDTO) {
        User findUser = userRepository.findByUsername(reqDTO.username())
                .orElseThrow(() -> new Exception404("유저네임 혹은 비밀번호가 일치하지 않습니다"));
        if (!bCryptPasswordEncoder.matches(reqDTO.password(), findUser.getPassword()))
            throw new Exception401("유저네임 혹은 비밀번호가 일치하지 않습니다");
        return JwtUtil.create(findUser);
    }

    public Map<String, Object> 유저네임중복체크(String username) {
        Map<String, Object> mapDTO = new HashMap<>();

        if (userRepository.findByUsername(username).isPresent()) {
            mapDTO.put("available", false);
        } else {
            mapDTO.put("available", true);
        }
        return mapDTO;
    }

}
