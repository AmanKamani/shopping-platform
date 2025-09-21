package com.example.userservice.service.impl;

import com.example.userservice.constant.ApplicationErrorCodes;
import com.example.userservice.dto.request.UpdateRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.AppUser;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.AppUserRepository;
import com.example.userservice.service.UserService;
import com.shoppingplatform.commonlib.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements UserService {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse findById(String id) {
        return repository.findById(UUID.fromString(id))
                .map(UserMapper::fromEntity)
                .orElseThrow(() -> new BaseException(ApplicationErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserResponse updateById(String id, UpdateRequest request) {
        AppUser appUser = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new BaseException(ApplicationErrorCodes.USER_NOT_FOUND));

        appUser.setPhoneNumber(request.phone());

        appUser = repository.save(appUser);
        return UserMapper.fromEntity(appUser);
    }

    @Override
    public void deleteById(String id) {
        validateUserExistenceById(id);

        repository.deleteById(UUID.fromString(id));
    }
    
    private void validateUserExistenceById(String id) {
        if (!repository.existsById(UUID.fromString(id))) {
            throw new BaseException(ApplicationErrorCodes.USER_NOT_FOUND);
        }
    }

    @Override
    public void updatePassword(String id, String oldPassword, String newPassword) {
        validateUserExistenceById(id);


        AppUser appUser = repository.findById(UUID.fromString(id))
                .filter(u -> passwordEncoder.matches(oldPassword, u.getPassword()))
                .orElseThrow(() -> new BaseException(ApplicationErrorCodes.INCORRECT_PASSWORD));

        appUser.setPassword(passwordEncoder.encode(newPassword));
        repository.save(appUser);
        log.info("Password updated successfully for [{}]", appUser.getId());


    }


}
