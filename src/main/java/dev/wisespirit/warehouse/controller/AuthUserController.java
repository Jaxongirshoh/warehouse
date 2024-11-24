package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.auth.AuthUserCreateDto;
import dev.wisespirit.warehouse.dto.auth.AuthUserDto;
import dev.wisespirit.warehouse.dto.auth.OrganizationDto;
import dev.wisespirit.warehouse.service.AuthRoleService;
import dev.wisespirit.warehouse.service.AuthUserService;
import dev.wisespirit.warehouse.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class AuthUserController {
    private final AuthUserService authUserService;
    private final AuthRoleService authRoleService;
    private final OrganizationService orgService;


    public AuthUserController(AuthUserService authUserService, AuthRoleService authRoleService, OrganizationService orgService) {
        this.authUserService = authUserService;
        this.authRoleService = authRoleService;
        this.orgService = orgService;
    }

    @PostMapping("/register/{organization_id}")
    public ResponseEntity<Object> save(@Valid @RequestBody AuthUserCreateDto dto,
                                       Errors errors,
                                       @PathVariable UUID organization_id){
        if (errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }

        if (authUserService.existsByPhoneNumberAndEmail(dto.phoneNumber())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Optional<OrganizationDto> organization = orgService.findOrganizationById(organization_id);
        if (!organization.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<AuthUserDto> optional = authUserService.save(dto,organization.get());
        if (optional.isPresent()){
            return new ResponseEntity<>(optional.get(),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> assignRoleToUser(@PathVariable UUID userId,
                                              @PathVariable UUID roleId ){
        Optional<AuthUserDto> userDto = authUserService.findById(userId);
        if (userDto.isPresent()){
            authRoleService.findById(roleId).ifPresent(authRole -> authUserService.saveRole(authRole,userId));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUserDto> getUserById(@PathVariable UUID id) {
        Optional<AuthUserDto> optionalDto = authUserService.findById(id);
        if (optionalDto.isPresent()) {
            return ResponseEntity.ok(optionalDto.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
