package com.getinthere.ncs.user.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {
    public record TeacherJoin(
            @NotEmpty(message = "유저네임은 공백일 수 없습니다") @Size(min = 3, max = 20, message = "유저네임은 3자에서 20자 사이여야 합니다") String username,

            @NotEmpty(message = "비밀번호는 공백일 수 없습니다") @Size(min = 4, max = 20, message = "비밀번호는 4자에서 20자 사이여야 합니다") String password,

            @NotEmpty(message = "이메일은 공백일 수 없습니다") @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "유효한 이메일 주소를 입력해주세요") String email,

            @NotEmpty(message = "이름은 공백일 수 없습니다") String fullName,

            String sign) {
    }

    public record StudentJoin(
            @NotEmpty(message = "유저네임은 공백일 수 없습니다") @Size(min = 3, max = 20, message = "유저네임은 3자에서 20자 사이여야 합니다") String username,

            @NotEmpty(message = "비밀번호는 공백일 수 없습니다") @Size(min = 4, max = 20, message = "비밀번호는 4자에서 20자 사이여야 합니다") String password,

            @NotEmpty(message = "이메일은 공백일 수 없습니다") @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "유효한 이메일 주소를 입력해주세요") String email,

            @NotEmpty(message = "이름은 공백일 수 없습니다") String fullName,

            @NotNull(message = "과정 ID는 공백일 수 없습니다") Long courseId,

            @NotEmpty(message = "생년월일은 공백일 수 없습니다") String birthday) {
    }

    public record EmpJoin(
            @NotEmpty(message = "유저네임은 공백일 수 없습니다") @Size(min = 3, max = 20, message = "유저네임은 3자에서 20자 사이여야 합니다") String username,

            @NotEmpty(message = "비밀번호는 공백일 수 없습니다") @Size(min = 4, max = 20, message = "비밀번호는 4자에서 20자 사이여야 합니다") String password,

            @NotEmpty(message = "이메일은 공백일 수 없습니다") @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "유효한 이메일 주소를 입력해주세요") String email,

            @NotEmpty(message = "이름은 공백일 수 없습니다") String fullName,

            String sign // 서명은 선택사항이므로 유효성 검증 어노테이션을 붙이지 않았습니다.
    ) {
    }

    public record Login(
            @NotEmpty(message = "유저네임은 공백일 수 없습니다") String username,

            @NotEmpty(message = "비밀번호는 공백일 수 없습니다") String password) {
    }
}
