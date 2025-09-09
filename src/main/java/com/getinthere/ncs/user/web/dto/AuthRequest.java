package com.getinthere.ncs.user.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AuthRequest {
    public record TeacherJoinDTO(
            @NotEmpty(message = "유저네임은 공백일 수 없습니다") @Size(min = 3, max = 20, message = "유저네임은 3자에서 20자 사이여야 합니다") String username,

            @NotEmpty(message = "비밀번호는 공백일 수 없습니다") @Size(min = 4, max = 20, message = "비밀번호는 4자에서 20자 사이여야 합니다") String password,

            @NotEmpty(message = "이메일은 공백일 수 없습니다") @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "유효한 이메일 주소를 입력해주세요") String email,

            @NotEmpty(message = "이름은 공백일 수 없습니다") String fullName,

            String sign) {
    }

    public record StudentJoinDTO(
            @NotEmpty(message = "유저네임은 공백일 수 없습니다") @Size(min = 3, max = 20, message = "유저네임은 3자에서 20자 사이여야 합니다") String username,

            @NotEmpty(message = "비밀번호는 공백일 수 없습니다") @Size(min = 4, max = 20, message = "비밀번호는 4자에서 20자 사이여야 합니다") String password,

            @NotEmpty(message = "이메일은 공백일 수 없습니다") @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "유효한 이메일 주소를 입력해주세요") String email,

            @NotEmpty(message = "이름은 공백일 수 없습니다") String fullName,

            @NotEmpty(message = "생년월일은 공백일 수 없습니다") String birthday) {
    }

    public record LoginDTO(
            @NotEmpty(message = "유저네임은 공백일 수 없습니다") String username,

            @NotEmpty(message = "비밀번호는 공백일 수 없습니다") String password) {
    }
}
