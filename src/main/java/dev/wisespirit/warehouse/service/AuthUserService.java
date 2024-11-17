package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.dto.AuthUserCreateDto;
import dev.wisespirit.warehouse.dto.AuthUserResponseDto;
import dev.wisespirit.warehouse.entity.auth.AuthUser;
import dev.wisespirit.warehouse.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    private final AuthUserRepository authUserRepository;

    public AuthUserService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    public AuthUserResponseDto save(AuthUserCreateDto dto){
        AuthUser authUser = new AuthUser();
        authUser.setEmail(dto.email());
        authUser.setPassword(dto.password());
        authUser.setOrganizationName(dto.organizationName());
        authUser.setPhoneNumber(dto.phoneNumber());
        AuthUser savedUser = authUserRepository.save(authUser);
        return new AuthUserResponseDto(savedUser.getOrganizationName(),savedUser.getPhoneNumber(),savedUser.getEmail());
    }

    public AuthUserResponseDto findByEmail(String email){
        AuthUser authUser = authUserRepository.findByEmail(email);
        return new AuthUserResponseDto(authUser.getOrganizationName(),authUser.getPhoneNumber(),authUser.getEmail());
    }
}
