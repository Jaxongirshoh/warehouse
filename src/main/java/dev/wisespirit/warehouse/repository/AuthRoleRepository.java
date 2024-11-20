package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.auth.AuthRole;
import dev.wisespirit.warehouse.entity.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthRoleRepository extends JpaRepository<AuthRole, UUID> {
}
