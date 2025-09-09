package com.getinthere.ncs.course.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getinthere.ncs.course.application.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
