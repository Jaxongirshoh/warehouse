package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.dto.auth.AuthUserCreateDto;
import dev.wisespirit.warehouse.dto.auth.AuthUserDto;
import dev.wisespirit.warehouse.dto.auth.OrganizationDto;
import dev.wisespirit.warehouse.entity.auth.AuthPermission;
import dev.wisespirit.warehouse.entity.auth.AuthRole;
import dev.wisespirit.warehouse.entity.auth.AuthUser;
import dev.wisespirit.warehouse.entity.auth.Organization;
import dev.wisespirit.warehouse.repository.AuthRoleRepository;
import dev.wisespirit.warehouse.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
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

    public Optional<AuthUserDto> save(AuthUserCreateDto dto, OrganizationDto orgDto){
        Organization organization = new Organization();
        organization.setEmail(orgDto.email());
        organization.setOrganizationName(orgDto.organizationName());
        organization.setPhoneNumber(orgDto.phoneNumber());
    //    organization.setOrgImage(orgDto.orgImage());
        AuthUser authUser = new AuthUser();
        authUser.setPassword(dto.password());
        authUser.setSurname(dto.surname());
        authUser.setPhoneNumber(dto.phoneNumber());
        AuthUser savedUser = authUserRepository.save(authUser);
        return Optional.of(new AuthUserDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getSurname(),
                savedUser.getPhoneNumber(),
                savedUser.getOrganizationId()));
    }


    public Optional<AuthUserDto> findById(Long id){
        AuthUser authUser = authUserRepository.findById(id).orElse(null);
        if(authUser == null){
            return Optional.empty();
        }
        return Optional.of(new AuthUserDto(authUser.getId(),authUser.getName(),authUser.getSurname(),authUser.getPhoneNumber(),authUser.getOrganizationId()));
    }


    public boolean existsByPhoneNumber(String phoneNumber) {
        return authUserRepository.existsByPhoneNumber(phoneNumber);
    }

    public void saveRole(AuthRole authRole,Long id) {
        AuthUser authUser = authUserRepository.findById(id).get();
        authUser.getRoles().add(authRole);
        authRoleRepository.save(authRole);
    }

    public Optional<List<AuthUserDto>> findByOrganizationId(Long organizationId) {
        List<AuthUser> authUserList = authUserRepository.findAllByOrganizationId(organizationId);
        List<AuthUserDto> dtoList = new ArrayList<>();
        if (authUserList.isEmpty()){
            return Optional.empty();
        }
        authUserList.forEach(authUser ->
                dtoList.add(new AuthUserDto(
                        authUser.getId(),
                        authUser.getName(),
                        authUser.getSurname(),
                        authUser.getPassword(),
                        authUser.getOrganizationId())));
        return Optional.of(dtoList);
    }

    public Optional<List<AuthRole>> findRolesByUserId(Long userid) {
        AuthUser authUser = authUserRepository.findById(userid).orElse(null);
        if(authUser == null){
            return Optional.empty();
        }
        return Optional.ofNullable(authUser.getRoles());
    }

    public boolean existById(Long userid) {
        return authUserRepository.existsById(userid);
    }

    public Optional<List<AuthPermission>> findUserPermissions(Long userid) {

        return null;
    }
}
