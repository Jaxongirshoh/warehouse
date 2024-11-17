package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.dto.AuthUserCreateDto;
import dev.wisespirit.warehouse.dto.AuthUserResponseDto;
import dev.wisespirit.warehouse.entity.auth.AuthUser;
import dev.wisespirit.warehouse.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthUserService {
    private final AuthUserRepository authUserRepository;

    public AuthUserService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    public Optional<AuthUserResponseDto> save(AuthUserCreateDto dto){
        AuthUser authUser = new AuthUser();
        authUser.setEmail(dto.email());
        authUser.setPassword(dto.password());
        authUser.setOrganizationName(dto.organizationName());
        authUser.setPhoneNumber(dto.phoneNumber());
        AuthUser savedUser = authUserRepository.save(authUser);
        return Optional.of(new AuthUserResponseDto(savedUser.getOrganizationName(),savedUser.getPhoneNumber(),savedUser.getEmail()));
    }

    public Optional<AuthUserResponseDto> findByEmail(String email){
        AuthUser authUser = authUserRepository.findByEmail(email).orElse(null);
        if (authUser == null) {
            return Optional.empty();
        }
        return Optional.of(new AuthUserResponseDto(authUser.getOrganizationName(),authUser.getPhoneNumber(),authUser.getEmail()));

    }

    public Optional<AuthUserResponseDto> findById(UUID id){
        AuthUser authUser = authUserRepository.findById(id).orElse(null);
        if(authUser == null){
            return Optional.empty();
        }
        return Optional.of(new AuthUserResponseDto(authUser.getOrganizationName(),authUser.getPhoneNumber(),authUser.getEmail()));
    }
}
