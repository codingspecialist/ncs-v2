package com.getinthere.ncs.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getinthere.ncs._core.utils.Resp;
import com.getinthere.ncs.user.application.service.UserService;
import com.getinthere.ncs.user.web.dto.AuthRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> healthCheck() {
        return Resp.ok("ok");
    }

    @PostMapping("/teachers/join")
    public ResponseEntity<?> teacherJoin(AuthRequest.TeacherJoinDTO reqDTO) {
        var respDTO = userService.강사_회원가입(reqDTO);
        return Resp.ok(respDTO);
    }

    @PostMapping("/students/join")
    public ResponseEntity<?> studentJoin(AuthRequest.StudentJoinDTO reqDTO) {
        var respDTO = userService.학생_회원가입(reqDTO);
        return Resp.ok(respDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest.LoginDTO requestDTO, Errors errors) {
        var respDTO = userService.로그인(requestDTO);
        return Resp.ok(respDTO);
    }

    @GetMapping("/check-username")
    public ResponseEntity<?> getUsername(@RequestParam String username) {
        var respDTO = userService.유저네임중복체크(username);
        return Resp.ok(respDTO);
    }
}
