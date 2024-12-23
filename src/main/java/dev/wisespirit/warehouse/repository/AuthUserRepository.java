package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    List<AuthUser> findAllByOrganizationId(Long organizationId);

    AuthUser findByPhoneNumber(String phoneNumber);
}
