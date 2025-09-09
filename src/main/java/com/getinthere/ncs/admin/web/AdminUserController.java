package com.getinthere.ncs.admin.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getinthere.ncs.user.application.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin/users")
@RequiredArgsConstructor
@RestController
public class AdminUserController {

    private final UserService userService;

    // 전체 강사 목록

    // 강사 승인 (추후 비용 받기) 1명당 월 5천원

    // 강사 승인취소
}
