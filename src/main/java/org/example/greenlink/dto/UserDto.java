package org.example.greenlink.dto;

import lombok.*;
import org.example.greenlink.domain.User;

public class UserDto {

    // Signup Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupReqDto {
        @jakarta.validation.constraints.NotBlank
        @jakarta.validation.constraints.Size(min = 4, max = 20)
        @jakarta.validation.constraints.Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$")
        public String username;
        @jakarta.validation.constraints.NotBlank
        @jakarta.validation.constraints.Size(min = 8, max = 64)
        public String password;
        @jakarta.validation.constraints.Email
        @jakarta.validation.constraints.Size(max = 255)
        public String email;
        @jakarta.validation.constraints.NotBlank
        @jakarta.validation.constraints.Size(min = 1, max = 20)
        public String nickname;
        @jakarta.validation.constraints.Size(max = 20)
        public String phoneNumber;
        @jakarta.validation.constraints.Size(max = 255)
        public String address;

        public User toEntity(String encodedPassword) {
            return User.of(getUsername(), encodedPassword, getEmail(), getNickname(), getPhoneNumber(), getAddress());
        }
    }

    // UserId Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UserIdResDto {
        Long userId;

        public static UserIdResDto toUserIdResDto(User user) {
            return UserIdResDto.builder().userId(user.getId()).build();
        }
    }

    // Login Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class LoginReqDto {
        public String username;
        public String password;
    }
}
