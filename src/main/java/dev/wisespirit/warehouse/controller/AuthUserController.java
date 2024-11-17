package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.AuthUserCreateDto;
import dev.wisespirit.warehouse.dto.AuthUserResponseDto;
import dev.wisespirit.warehouse.service.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class AuthUserController {
    private final AuthUserService authUserService;

    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> save(@Valid @RequestBody AuthUserCreateDto dto, Errors errors){
        if (errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Optional<AuthUserResponseDto> saved = authUserService.save(dto);
        if (saved.isPresent()){
            return new ResponseEntity<>(saved.get(),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AuthUserResponseDto> getUserById(@PathVariable UUID id) {
        Optional<AuthUserResponseDto> optionalDto = authUserService.findById(id);
        if (optionalDto.isPresent()) {
            return ResponseEntity.ok(optionalDto.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
