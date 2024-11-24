package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.dto.auth.OrganizationCreateDto;
import dev.wisespirit.warehouse.dto.auth.OrganizationDto;
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
            new OrganizationDto(organization.getOrganizationName(), organization.getPhoneNumber(), organization.getEmail(), organization.getOrgImage());
        }
        return Optional.empty();
    }

    public boolean existsByPhoneNumberAndEmailAndOrganizationName(String phoneNumber, String email, String organizationName) {
        return organizationRepository.existsOrganizationByPhoneNumberAndAndEmailAndOrganizationName(phoneNumber, email, organizationName);
    }

    public Optional<OrganizationDto> save(OrganizationCreateDto dto, MultipartFile multipartFile) {
        Organization organization = new Organization();
        try {
            organization.setOrganizationName(dto.organizationName());
            organization.setPhoneNumber(dto.phoneNumber());
            organization.setEmail(dto.email());
            organization.setOrgImage(multipartFile.getBytes());
            organization.setImageName(multipartFile.getOriginalFilename());
            organization.setImageType(multipartFile.getContentType());
            Organization saved = organizationRepository.save(organization);
            OrganizationDto orgDto = new OrganizationDto(saved.getOrganizationName(),
                    saved.getPhoneNumber(),
                    saved.getImageName(),
                    saved.getOrgImage());
            return Optional.of(orgDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        organization.setOrganizationName(dto.organizationName());
        organization.setPhoneNumber(dto.phoneNumber());
        organization.setEmail(dto.email());
        organization.setOrgImage(null);
        organization.setImageName(multipartFile.getOriginalFilename());
        organization.setImageType(multipartFile.getContentType());
        Organization saved = organizationRepository.save(organization);
        OrganizationDto orgDto = new OrganizationDto(saved.getOrganizationName(),
                saved.getPhoneNumber(),
                saved.getImageName(),
                saved.getOrgImage());
        return Optional.of(orgDto);

    }

    public boolean existsById(UUID id) {
        return organizationRepository.existsById(id);
    }

    public Optional<OrganizationDto> updateOrganization(OrganizationCreateDto dto, UUID id, MultipartFile multipartFile) {
        Organization organization = new Organization();
        if (dto.organizationName() != null) {
            organization.setOrganizationName(dto.organizationName());
        }
        if (dto.phoneNumber() != null) {
            organization.setPhoneNumber(dto.phoneNumber());
        }
        if (dto.email() != null) {
            organization.setEmail(dto.email());
        }
        if (dto.orgImage() != null) {
            organization.setOrgImage(dto.orgImage());
        }
        if (dto.password() != null) {
            organization.setPassword(dto.password());
        }
        if (!multipartFile.isEmpty()) {
            organization.setImageName(multipartFile.getOriginalFilename());
            organization.setImageType(multipartFile.getContentType());
            try {
                organization.setOrgImage(multipartFile.getBytes());

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        Organization org = organizationRepository.save(organization);
        OrganizationDto orgDto = new OrganizationDto();
        orgDto.



    }
}
