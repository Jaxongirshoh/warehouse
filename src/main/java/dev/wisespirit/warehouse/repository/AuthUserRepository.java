package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);
}
