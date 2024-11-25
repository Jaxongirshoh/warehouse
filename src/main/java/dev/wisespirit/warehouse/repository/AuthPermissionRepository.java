package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.auth.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Long> {
}
