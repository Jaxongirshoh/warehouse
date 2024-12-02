package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.entity.auth.AuthPermission;
import dev.wisespirit.warehouse.service.AuthPermissionService;
import dev.wisespirit.warehouse.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/permissions")
public class AuthPermissionController {
    private final AuthPermissionService authPermissionService;

    public AuthPermissionController(AuthPermissionService authPermissionService) {
        this.authPermissionService = authPermissionService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> createPermission(@Valid @RequestBody AuthPermission authPermission){
        if (authPermissionService.existByName(authPermission.getName())) {
            return new ResponseEntity<>(ApiResponse.error("permission already exist",null), HttpStatus.BAD_REQUEST);
        }
        Optional<AuthPermission> permission = authPermissionService.createPermission(authPermission);
        if (permission.isPresent()) {
            return new ResponseEntity<>(ApiResponse.success(permission.get()),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(ApiResponse.error("something went wrong",null), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<ApiResponse> getPermission(@PathVariable Long permissionId){
        if (!authPermissionService.existById(permissionId)) {
            return new ResponseEntity<>(ApiResponse.error("permission does not exist",null), HttpStatus.NOT_FOUND);
        }
        Optional<AuthPermission> optional = authPermissionService.findById(permissionId);
        if (optional.isPresent()) {
            return new ResponseEntity<>(ApiResponse.success(optional.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(ApiResponse.error("something went wrong",null), HttpStatus.BAD_REQUEST);
    }
}
