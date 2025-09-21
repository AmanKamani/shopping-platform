package com.example.userservice.service.impl;

import com.example.userservice.constant.ApplicationErrorCodes;
import com.example.userservice.dto.request.PatchUserRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.AppUser;
import com.example.userservice.enums.UserStatus;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.AppUserRepository;
import com.example.userservice.service.AdminService;
import com.shoppingplatform.commonlib.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AppUserRepository repository;

    @Override
    public List<UserResponse> getAllUsers(int size, int page) {
        return repository.findAll(PageRequest.of(page, size))
                .stream()
                .map(UserMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(String id) {
        AppUser user = findByIdOrThrow(id);
        return UserMapper.fromEntity(user);
    }

    private AppUser findByIdOrThrow(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new BaseException(ApplicationErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserResponse updateRole(String id, PatchUserRequest request) {
        AppUser user = findByIdOrThrow(id);
        if (request.getType().equals(PatchUserRequest.PatchType.ADD)) {
            user.getRoles().addAll(request.getRoles());
        } else {
            user.getRoles().removeAll(request.getRoles());
        }

        user = repository.save(user);
        return UserMapper.fromEntity(user);
    }

    @Override
    public void deleteUser(String id) {
        AppUser user = findByIdOrThrow(id);
        user.setStatus(UserStatus.DELETED);
        repository.save(user);
    }
}
