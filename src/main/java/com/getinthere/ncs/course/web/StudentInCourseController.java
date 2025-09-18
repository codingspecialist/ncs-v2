package com.getinthere.ncs.course.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/student-in-course")
@RequiredArgsConstructor
@RestController
public class StudentInCourseController {
    // 유저네임, 이름으로 검색한 학생 목록 (User테이블의 유저네임, Student테이블에 학생이름)

    // 과정에 학생 등록 (검색한 목록에서 멀티 선택하여 등록) student_id, course_id

    // 과정에 학생 삭제

    // 과정에 학생 중탈이유, 총평, 수준, 중탈날짜, 상태(취업, 중도탈락, 재학중) 수정
}
