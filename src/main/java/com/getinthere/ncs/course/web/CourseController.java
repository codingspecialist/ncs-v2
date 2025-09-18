package com.getinthere.ncs.course.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getinthere.ncs.course.application.service.CourseService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/courses")
@RequiredArgsConstructor
@RestController
public class CourseController {

    private final CourseService courseService;

    // 과정 등록

    // 과정 목록

    // 과정 정보 (기존 과정 불러오기)

    // 과정 상세 (탭바뷰) (과정정보, 과정별 교과목목록, 과정별 학생목록)

    // 과정 수정

    // 과정 삭제
}
