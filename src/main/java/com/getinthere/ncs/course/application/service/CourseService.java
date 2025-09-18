package com.getinthere.ncs.course.application.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.getinthere.ncs.course.application.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@RestController
public class CourseService {

    private final CourseRepository courseRepository;

}
