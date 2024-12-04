package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.auth.AuthRoleDto;
import dev.wisespirit.warehouse.entity.auth.AuthPermission;
import dev.wisespirit.warehouse.entity.auth.AuthRole;
import dev.wisespirit.warehouse.entity.auth.AuthUser;
import dev.wisespirit.warehouse.service.AuthPermissionService;
import dev.wisespirit.warehouse.service.AuthRoleService;
import dev.wisespirit.warehouse.service.AuthUserService;
import dev.wisespirit.warehouse.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
public class AuthRoleController {
    private final AuthRoleService authRoleService;
    private final AuthPermissionService authPermissionService;
    private final AuthUserService authUserService;

    public AuthRoleController(AuthRoleService authRoleService, AuthPermissionService authPermissionService, AuthUserService authUserService) {
        this.authRoleService = authRoleService;
        this.authPermissionService = authPermissionService;
        this.authUserService = authUserService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> createRole(@RequestBody AuthRoleDto role) {
        try {
            AuthRole authRole = new AuthRole();
            authRole.setName(role.name());
            authRole.setDescription(role.description());
            Optional<AuthRole> saved = authRoleService.createRole(authRole);
            return saved.<ResponseEntity<ApiResponse>>map(value -> new ResponseEntity<>(ApiResponse.success(value), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(ApiResponse.error("error during save", null), HttpStatus.BAD_REQUEST));
        }catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.error("error during save", null), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponse> getRole(@PathVariable Long roleId) {
        if (authRoleService.existById(roleId)) {
            Optional<AuthRole> optional = authRoleService.findById(roleId);
            if (optional.isPresent()) {
                return new ResponseEntity<>(ApiResponse.success(optional.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ApiResponse.error("not found", null), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<ApiResponse> wireRoleWithPermissions(@PathVariable Long roleId,
                                                               @PathVariable Long permissionId) {
        if (!authPermissionService.existById(permissionId)) {
            return new ResponseEntity<>(ApiResponse.error("permission not found", null), HttpStatus.NOT_FOUND);
        }
        if (!authRoleService.existById(roleId)) {
            return new ResponseEntity<>(ApiResponse.error("role not found", null), HttpStatus.NOT_FOUND);
        }
        authRoleService.findById(roleId).ifPresent(role -> authRoleService.savePermission(roleId, permissionId));
        return new ResponseEntity<>(ApiResponse.success(), HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<ApiResponse> getRoleByUserId(@PathVariable Long userid) {
        if (!authUserService.existById(userid)) {
            return new ResponseEntity<>(ApiResponse.error("user not found", null), HttpStatus.NOT_FOUND);
        }
        Optional<List<AuthRole>> permissions = authUserService.findRolesByUserId(userid);
        if (permissions.isPresent()) {
            return new ResponseEntity<>(ApiResponse.success(permissions.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApiResponse.error("not found", null), HttpStatus.NOT_FOUND);
    }
}
