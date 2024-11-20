package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.dto.OrganizationCreateDto;
import dev.wisespirit.warehouse.dto.OrganizationDto;
import dev.wisespirit.warehouse.entity.auth.Organization;
import dev.wisespirit.warehouse.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Optional<OrganizationDto> findOrganizationById(UUID id) {
        Optional<Organization> opt = organizationRepository.findById(id);
        if (opt.isPresent()) {
            Organization organization = opt.get();
            new OrganizationDto(organization.getOrganizationName(),organization.getPhoneNumber(),organization.getEmail(),organization.getOrgImage());
        }
        return Optional.empty();
    }

    public boolean existsByPhoneNumberAndEmailAndOrganizationName(String phoneNumber, String email, String organizationName) {
        return organizationRepository.existsOrganizationByPhoneNumberAndAndEmailAndOrganizationName(phoneNumber,email,organizationName);
    }

    public Optional<OrganizationDto> save(OrganizationCreateDto dto, MultipartFile multipartFile) {
    }
}
