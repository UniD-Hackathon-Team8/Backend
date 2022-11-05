package com.meltingzone.meltingzone.service;

import com.meltingzone.meltingzone.auth.JwtTokenProvider;
import com.meltingzone.meltingzone.domain.User;
import com.meltingzone.meltingzone.dto.user.UserRequestDto;
import com.meltingzone.meltingzone.dto.user.UserResponseDto;
import com.meltingzone.meltingzone.repository.UserRepository;
import com.meltingzone.meltingzone.util.CustomException;
import com.meltingzone.meltingzone.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserResponseDto signUp(UserRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ResponseCode.USER_DUPLICATED);
        }

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        User user = userRepository.save(new User(requestDto));

        return new UserResponseDto(jwtTokenProvider.createToken(user.getEmail()));
    }

    public UserResponseDto signIn(UserRequestDto requestDto) {
       User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(
               () -> new CustomException(ResponseCode.LOGIN_FAILED)
        );

       if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
           throw new CustomException(ResponseCode.LOGIN_FAILED);
       }

        return new UserResponseDto(jwtTokenProvider.createToken(user.getEmail()));
    }
}
