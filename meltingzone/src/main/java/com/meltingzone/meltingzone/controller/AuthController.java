package com.meltingzone.meltingzone.controller;

import com.meltingzone.meltingzone.dto.user.UserRequestDto;
import com.meltingzone.meltingzone.service.UserService;
import com.meltingzone.meltingzone.util.ResponseCode;
import com.meltingzone.meltingzone.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> signUp(@RequestBody UserRequestDto requestDto) {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                userService.signUp(requestDto)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody UserRequestDto requestDto) {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                userService.signIn(requestDto)
        );
    }
}
