package com.getinthere.ncs.user.web.dto;

import java.util.Optional;

import com.getinthere.ncs.user.application.domain.Student;
import com.getinthere.ncs.user.application.domain.Teacher;
import com.getinthere.ncs.user.application.domain.User;

public class AuthResponse {
    public record TeacherDTO(
            Long teacherId,
            String sign,
            String fullName) {
        public TeacherDTO(Teacher teacher) {
            this(teacher.getId(), teacher.getSign(), teacher.getFullName());
        }
    }

    public record StudentDTO(
            Long studentId,
            String fullName,
            String birthday) {
        public StudentDTO(Student student) {
            this(student.getId(), student.getFullName(), student.getBirthday());
        }
    }

    public record DTO(
            Long userId,
            String username,
            String email,
            String roles,
            Optional<TeacherDTO> teacher,
            Optional<StudentDTO> student) {

        public DTO(User user) {
            this(user.getId(), user.getUsername(), user.getEmail(), user.getRoles(),
                    Optional.ofNullable(user.getTeacher()).map(TeacherDTO::new),
                    Optional.ofNullable(user.getStudent()).map(StudentDTO::new));
        }
    }

}
