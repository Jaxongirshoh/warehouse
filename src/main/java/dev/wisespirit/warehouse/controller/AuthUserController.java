package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.auth.AuthUserCreateDto;
import dev.wisespirit.warehouse.dto.auth.AuthUserDto;
import dev.wisespirit.warehouse.dto.auth.OrganizationDto;
import dev.wisespirit.warehouse.entity.auth.AuthPermission;
import dev.wisespirit.warehouse.entity.auth.AuthRole;
import dev.wisespirit.warehouse.service.AuthRoleService;
import dev.wisespirit.warehouse.service.AuthUserService;
import dev.wisespirit.warehouse.service.OrganizationService;
import dev.wisespirit.warehouse.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class AuthUserController {
    private final AuthUserService authUserService;
    private final AuthRoleService authRoleService;
    private final OrganizationService orgService;
    private final OrganizationService organizationService;


    public AuthUserController(AuthUserService authUserService, AuthRoleService authRoleService, OrganizationService orgService, OrganizationService organizationService) {
        this.authUserService = authUserService;
        this.authRoleService = authRoleService;
        this.orgService = orgService;
        this.organizationService = organizationService;
    }

    @PostMapping("/create/{organization_id}")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody AuthUserCreateDto dto,
                                            Errors errors,
                                            @PathVariable Long organization_id) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(ApiResponse.error("error ", errors.getAllErrors()), HttpStatus.BAD_REQUEST);
        }

        if (authUserService.existsByPhoneNumber(dto.phoneNumber())) {
            return new ResponseEntity<>(ApiResponse.error("phone number already exist", null), HttpStatus.BAD_REQUEST);
        }
        Optional<OrganizationDto> organization = orgService.findOrganizationById(organization_id);
        if (!organization.isPresent()) {
            return new ResponseEntity<>(ApiResponse.error("organization not found", null), HttpStatus.BAD_REQUEST);
        }
        Optional<AuthUserDto> optional = authUserService.save(dto, organization_id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(ApiResponse.success(optional.get()), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(ApiResponse.error("bad request", null), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<ApiResponse> assignRoleToUser(@PathVariable Long userId,
                                                        @PathVariable Long roleId) {
        Optional<AuthUserDto> userDto = authUserService.findById(userId);
        if (userDto.isPresent()) {
            authRoleService.findById(roleId).ifPresent(authRole -> authUserService.saveRole(authRole, userId));
            return new ResponseEntity<>(ApiResponse.success(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(ApiResponse.error("user not found", null), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/roles/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable(name = "userId") Long id) {
        Optional<AuthUserDto> optionalDto = authUserService.findById(id);
        if (optionalDto.isPresent()) {
            return new ResponseEntity(ApiResponse.success(optionalDto.get()), HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(ApiResponse.error("not found", null), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<ApiResponse> getEmployeesByOrganizationId(@PathVariable Long organizationId) {
        if (organizationService.existsById(organizationId)) {
            Optional<List<AuthUserDto>> list = authUserService.findByOrganizationId(organizationId);
            if (list.isPresent()) {
                return new ResponseEntity<>(ApiResponse.success(list.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ApiResponse.error("not found", null), HttpStatus.NOT_FOUND);
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

    @GetMapping("/permissions/{userId}")
    public ResponseEntity<ApiResponse> getPermissionsByUserId(@PathVariable Long userid) {
        if (!authUserService.existById(userid)) {
            return new ResponseEntity<>(ApiResponse.error("user not found",null),HttpStatus.NOT_FOUND);
        }
        Optional<List<AuthPermission>> permissions = authUserService.findUserPermissions(userid);
        if (permissions.isPresent()) {
            return new ResponseEntity<>(ApiResponse.success(permissions.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(ApiResponse.error("not found permissions for this employee", null), HttpStatus.NOT_FOUND);
    }







/*
    @GetMapping("/{userid}")
    public ResponseEntity<ApiResponse> getRoleByUserId(@PathVariable Long userid) {
        return getApiResponseResponseEntity(userid, authRoleService, authUserService);
    }

    static ResponseEntity<ApiResponse> getApiResponseResponseEntity(@PathVariable Long userid, AuthRoleService authRoleService, AuthUserService authUserService) {
        if (!authRoleService.existById(userid)) {
            return new ResponseEntity<>(ApiResponse.error("user not found", null), HttpStatus.NOT_FOUND);
        }
        Optional<List<AuthRole>> permissions = authUserService.findRolesByUserId(userid);
        if (permissions.isPresent()) {
            return new ResponseEntity<>(ApiResponse.success(permissions.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApiResponse.error("not found", null), HttpStatus.NOT_FOUND);
    }*/
}
