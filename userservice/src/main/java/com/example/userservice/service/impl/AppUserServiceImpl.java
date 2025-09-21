package com.example.userservice.service.impl;

import com.example.userservice.dto.request.UpdateRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.AppUser;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.AppUserRepository;
import com.example.userservice.service.UserService;
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
    public Optional<UserResponse> findById(String id) {
        return repository.findById(UUID.fromString(id))
                .map(UserMapper::fromEntity);
    }

    @Override
    public UserResponse updateById(String id, UpdateRequest request) {
        AppUser appUser = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("User not found"));

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
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void updatePassword(String id, String newPassword) {
        validateUserExistenceById(id);

        repository.findById(UUID.fromString(id))
                .map(data -> {
                    data.setPassword(passwordEncoder.encode(newPassword));
                    repository.save(data);
                    log.info("Password updated successfully for [{}]", data.getId());
                    return null;
                });


    }


}
