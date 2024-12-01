package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.entity.auth.AuthPermission;
import dev.wisespirit.warehouse.repository.AuthPermissionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthPermissionService {

    private final AuthPermissionRepository authPermissionRepository;

    public AuthPermissionService(AuthPermissionRepository authPermissionRepository) {
        this.authPermissionRepository = authPermissionRepository;
    }

    public boolean existById(Long permissionId) {
        return authPermissionRepository.existsById(permissionId);
    }

    public Optional<AuthPermission> createPermission(AuthPermission authPermission) {
        return Optional.of(authPermissionRepository.save(authPermission));
    }

    public boolean existByName(String permissionName) {
        return authPermissionRepository.existsByName(permissionName);
    }

    public Optional<AuthPermission> findById(Long permissionId) {
        return authPermissionRepository.findById(permissionId);
    }
}
