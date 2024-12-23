package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.auth.AuthUserCreateDto;
import dev.wisespirit.warehouse.dto.auth.AuthUserDto;
import dev.wisespirit.warehouse.dto.auth.AuthUserUpdateDto;
import dev.wisespirit.warehouse.dto.auth.OrganizationDto;
import dev.wisespirit.warehouse.entity.auth.AuthPermission;
import dev.wisespirit.warehouse.entity.auth.AuthRole;
import dev.wisespirit.warehouse.entity.auth.AuthUser;
import dev.wisespirit.warehouse.service.AuthRoleService;
import dev.wisespirit.warehouse.service.AuthUserService;
import dev.wisespirit.warehouse.service.OrganizationService;
import dev.wisespirit.warehouse.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody AuthUserCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Create a map of field errors
            Map<String, String> fieldErrors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existingValue, newValue) -> existingValue
                    ));

            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("Validation failed", fieldErrors));
        }
        try {
            if (dto==null){
                return new ResponseEntity<>(ApiResponse.error("bad request",null),HttpStatus.BAD_REQUEST);
            }
            AuthUser authUser = new AuthUser();
            authUser.setOrganizationId(dto.organizationId());
            authUser.setSurname(dto.surname());
            authUser.setPhoneNumber(dto.phoneNumber());
            authUser.setPassword(dto.password());
            authUser.setName(dto.name());
            try {
                String verify = authUserService.verify(authUser);
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("Authorization","Bearer "+verify);
                return new ResponseEntity<>(ApiResponse.success(verify), params,200);
            }catch (Exception e){
                return new ResponseEntity<>(ApiResponse.error(e.getMessage(),null),HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error("something wrong",null),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/create/{organization_id}")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody AuthUserCreateDto dto,
                                            Errors errors,
                                            @PathVariable Long organization_id) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(ApiResponse.error("error ", errors.getAllErrors()), HttpStatus.BAD_REQUEST);
        }

        try {
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
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }

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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable() Long id) {
        try {
            Optional<AuthUserDto> optionalDto = authUserService.findById(id);
            if (optionalDto.isPresent()) {
                return new ResponseEntity(ApiResponse.success(optionalDto.get()), HttpStatus.valueOf(200));
            }
            return new ResponseEntity<>(ApiResponse.error("not found", null), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error("something wrong", null), HttpStatus.BAD_REQUEST);
        }

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
/*
    @GetMapping("/roles/{userid}")
    public ResponseEntity<ApiResponse> getRoleByUserId(@PathVariable Long userid) {
        if (!authUserService.existById(userid)) {
            return new ResponseEntity<>(ApiResponse.error("user not found", null), HttpStatus.NOT_FOUND);
        }
        try {
            Optional<AuthUserDto> optional = authUserService.findById(userid);
            if (optional.isPresent()) {
                return new ResponseEntity<>(ApiResponse.success(optional.get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(ApiResponse.error("not found", null), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error("something wrong",null), HttpStatus.BAD_REQUEST);
        }

    }*/

    @GetMapping("/permissions/{userId}")
    public ResponseEntity<ApiResponse> getPermissionsByUserId(@PathVariable Long userid) {
        if (!authUserService.existById(userid)) {
            return new ResponseEntity<>(ApiResponse.error("user not found",null),HttpStatus.NOT_FOUND);
        }
        try {
            Optional<List<AuthPermission>> permissions = authUserService.findUserPermissions(userid);
            if (permissions.isPresent()) {
                return new ResponseEntity<>(ApiResponse.success(permissions.get()),HttpStatus.OK);
            }
            return new ResponseEntity<>(ApiResponse.error("not found permissions for this employee", null), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error("something wrong",null),HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId,
                                                  @RequestBody AuthUserUpdateDto dto) {
        if (!authUserService.existById(userId)){
            return new ResponseEntity<>(ApiResponse.error("user not found",null),HttpStatus.NOT_FOUND);
        }
        AuthUserDto authUserDto = authUserService.update(userId,dto);
        if (authUserDto == null) {
            return new ResponseEntity<>(ApiResponse.error("something went wrong",null),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ApiResponse.success(authUserDto), HttpStatus.OK);
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
