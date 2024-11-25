package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.repository.AuthPermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthPermissionService {

    private final AuthPermissionRepository authPermissionRepository;

    public AuthPermissionService(AuthPermissionRepository authPermissionRepository) {
        this.authPermissionRepository = authPermissionRepository;
    }
}
