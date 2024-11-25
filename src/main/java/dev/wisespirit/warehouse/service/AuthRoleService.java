package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.entity.auth.AuthRole;
import dev.wisespirit.warehouse.repository.AuthRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthRoleService {

    private final AuthRoleRepository authRoleRepository;

    public AuthRoleService(AuthRoleRepository authRoleRepository) {
        this.authRoleRepository = authRoleRepository;
    }

    public Optional<AuthRole> findById(Long id) {
        return authRoleRepository.findById(id);
    }

}
