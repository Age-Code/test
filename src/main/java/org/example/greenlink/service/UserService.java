package org.example.greenlink.service;

import org.example.greenlink.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto.UserIdResDto signup(UserDto.SignupReqDto signupReqDto);
    org.example.greenlink.dto.MvpDto.UserProfileRes getProfile(Long userId);
    org.example.greenlink.dto.MvpDto.UserProfileRes patchProfile(Long userId, org.example.greenlink.dto.MvpDto.UserProfilePatchReq req);
}
