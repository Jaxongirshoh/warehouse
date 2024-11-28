package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.entity.auth.AuthPermission;
import dev.wisespirit.warehouse.entity.auth.AuthRole;
import dev.wisespirit.warehouse.repository.AuthPermissionRepository;
import dev.wisespirit.warehouse.repository.AuthRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthRoleService {

    private final AuthRoleRepository authRoleRepository;
    private final AuthPermissionRepository authPermissionRepository;

    public AuthRoleService(AuthRoleRepository authRoleRepository, AuthPermissionRepository authPermissionRepository) {
        this.authRoleRepository = authRoleRepository;
        this.authPermissionRepository = authPermissionRepository;
    }

    public Optional<AuthRole> findById(Long id) {
        return authRoleRepository.findById(id);
    }

    public Optional<AuthRole> createRole(AuthRole role) {
        return Optional.of(authRoleRepository.save(role));
    }

    public boolean existById(Long roleId) {
        return authRoleRepository.existsById(roleId);
    }

    public void savePermission(Long roleId,Long permissionId) {
        Optional<AuthPermission> optional = authPermissionRepository.findById(permissionId);
        if (optional.isPresent()) {
            AuthRole authRole = authRoleRepository.findById(roleId).get();
            List<AuthPermission> permissions = authRole.getPermissions();
            if (!permissions.contains(optional.get())) {
                permissions.add(optional.get());
                authRole.setPermissions(permissions);
                authRoleRepository.save(authRole);
            }
        }
    }
}
