package com.example.userservice.repository;

import com.example.userservice.entity.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    boolean existsByUsernameOrEmail(String username, String email);

    Optional<AppUser> findByUsernameOrEmail(String username, String email);

    boolean existsByIdAndPassword(UUID id, String password);
}
