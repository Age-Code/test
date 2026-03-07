package org.example.greenlink.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.greenlink.dto.ApiResponse;
import org.example.greenlink.dto.MvpDto;
import org.example.greenlink.dto.UserDto;
import org.example.greenlink.security.PrincipalDetails;
import org.example.greenlink.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserRestController {

    final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserDto.UserIdResDto>> signup(@Valid @RequestBody UserDto.SignupReqDto signupReqDto){
        return ResponseEntity.ok(ApiResponse.success(userService.signup(signupReqDto)));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MvpDto.UserProfileRes>> me(@AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok(ApiResponse.success(userService.getProfile(principal.getUser().getId())));
    }

    @PatchMapping("/me")
    public ResponseEntity<ApiResponse<MvpDto.UserProfileRes>> patchMe(
            @AuthenticationPrincipal PrincipalDetails principal,
            @Valid @RequestBody MvpDto.UserProfilePatchReq req) {
        return ResponseEntity.ok(ApiResponse.success(userService.patchProfile(principal.getUser().getId(), req)));
    }
}
