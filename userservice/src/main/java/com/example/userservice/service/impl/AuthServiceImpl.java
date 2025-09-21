package com.example.userservice.service.impl;

import com.example.userservice.constant.ApplicationErrorCodes;
import com.example.userservice.dto.request.LoginRequest;
import com.example.userservice.dto.request.UserRegistrationRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.AppUser;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.AppUserRepository;
import com.example.userservice.service.AuthService;
import com.shoppingplatform.commonlib.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRegistrationRequest request) {
        if (userRepository.existsByUsernameOrEmail(request.username(), request.email())) {
            throw new BaseException(ApplicationErrorCodes.DUPLICATE_USER_EXIST, "Username or Email already exists");
        }
        AppUser user = UserMapper.fromDto(request, passwordEncoder.encode(request.password()));

        return UserMapper.fromEntity(userRepository.save(user));
    }

    @Override
    public void logout(String userId) {
        // TODO: Blacklist jwt token
    }


    @Override
    public String login(LoginRequest request) {
        AppUser appUser = userRepository.findByUsernameOrEmail(request.identity(), request.identity())
                .orElseThrow(() -> new BaseException(ApplicationErrorCodes.USER_NOT_FOUND, "Username/email not found"));

        if (!passwordEncoder.matches(request.password(), appUser.getPassword())) {
            throw new BaseException(ApplicationErrorCodes.INVALID_CREDENTIALS);
        }

        return "OK";
    }
}
