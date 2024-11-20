package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.dto.AuthUserCreateDto;
import dev.wisespirit.warehouse.dto.AuthUserDto;
import dev.wisespirit.warehouse.entity.auth.AuthRole;
import dev.wisespirit.warehouse.entity.auth.AuthUser;
import dev.wisespirit.warehouse.repository.AuthRoleRepository;
import dev.wisespirit.warehouse.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final AuthRoleRepository authRoleRepository;

    public AuthUserService(AuthUserRepository authUserRepository, AuthRoleRepository authRoleRepository) {
        this.authUserRepository = authUserRepository;
        this.authRoleRepository = authRoleRepository;
    }

    public Optional<AuthUserDto> save(AuthUserCreateDto dto){
        AuthUser authUser = new AuthUser();
        authUser.setPassword(dto.password());
        authUser.setName(dto.organizationName());
        authUser.setSurname(dto.surname());
        authUser.setPhoneNumber(dto.phoneNumber());
        AuthUser savedUser = authUserRepository.save(authUser);
        return Optional.of(new AuthUserDto(savedUser.getOrganizationName(),savedUser.getPhoneNumber(),savedUser.getEmail()));
    }

    public Optional<AuthUserDto> findByEmail(String email){
        AuthUser authUser = authUserRepository.findByEmail(email).orElse(null);
        if (authUser == null) {
            return Optional.empty();
        }
        return Optional.of(new AuthUserDto(authUser.getOrganizationName(),authUser.getPhoneNumber(),authUser.getEmail()));

    }

    public Optional<AuthUserDto> findById(UUID id){
        AuthUser authUser = authUserRepository.findById(id).orElse(null);
        if(authUser == null){
            return Optional.empty();
        }
        return Optional.of(new AuthUserDto(authUser.getOrganizationName(),authUser.getPhoneNumber(),authUser.getEmail()));
    }


    public boolean existsByPhoneNumberAndEmail(String phoneNumber, String email) {
        return authUserRepository.existsByPhoneNumberAndEmail(phoneNumber,email);
    }

    public void saveRole(AuthRole authRole,UUID id) {
        AuthUser authUser = authUserRepository.findById(id).get();
        authUser.getRoles().add(authRole);
        authRoleRepository.save(authRole);
    }
}
