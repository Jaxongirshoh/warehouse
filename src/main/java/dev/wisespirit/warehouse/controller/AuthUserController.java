package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.AuthUserCreateDto;
import dev.wisespirit.warehouse.dto.AuthUserDto;
import dev.wisespirit.warehouse.service.AuthRoleService;
import dev.wisespirit.warehouse.service.AuthUserService;
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


    public AuthUserController(AuthUserService authUserService, AuthRoleService authRoleService) {
        this.authUserService = authUserService;
        this.authRoleService = authRoleService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> save(@Valid @RequestBody AuthUserCreateDto dto, Errors errors){
        if (errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }

        if (authUserService.existsByPhoneNumberAndEmail(dto.phoneNumber(),dto.email())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Optional<AuthUserDto> optional = authUserService.save(dto);
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
