package org.example.greenlink.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.greenlink.domain.User;
import org.example.greenlink.dto.MvpDto;
import org.example.greenlink.dto.UserDto;
import org.example.greenlink.exception.BusinessException;
import org.example.greenlink.exception.ErrorCode;
import org.example.greenlink.repository.UserRepository;
import org.example.greenlink.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceimpl implements UserService {

    final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Signup
    @Override
    public UserDto.UserIdResDto signup(UserDto.SignupReqDto signupReqDto){

        User user = userRepository.findByUsername(signupReqDto.getUsername()).orElse(null);
        if(user != null) {
            throw new RuntimeException("Already exist");
        }

        String encoded = bCryptPasswordEncoder.encode(signupReqDto.getPassword());
        user = userRepository.save(signupReqDto.toEntity(encoded));

        return UserDto.UserIdResDto.toUserIdResDto(user);
    }

    @Override
    public MvpDto.UserProfileRes getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "존재하지 않는 데이터입니다."));
        return toProfile(user);
    }

    @Override
    public MvpDto.UserProfileRes patchProfile(Long userId, MvpDto.UserProfilePatchReq req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "존재하지 않는 데이터입니다."));
        if (req.getNickname() != null) user.setNickname(req.getNickname());
        if (req.getPhoneNumber() != null) user.setPhoneNumber(req.getPhoneNumber());
        if (req.getAddress() != null) user.setAddress(req.getAddress());
        return toProfile(user);
    }

    private MvpDto.UserProfileRes toProfile(User user) {
        return MvpDto.UserProfileRes.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }
}
