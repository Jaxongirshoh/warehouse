package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.Exceptions.EntityNotFoundException;
import dev.wisespirit.warehouse.dto.auth.OrganizationCreateDto;
import dev.wisespirit.warehouse.dto.auth.OrganizationDto;
import dev.wisespirit.warehouse.entity.auth.Organization;
import dev.wisespirit.warehouse.repository.OrganizationRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Optional<OrganizationDto> findOrganizationById(Long id) {
        Optional<Organization> opt = organizationRepository.findById(id);
        if (opt.isPresent()) {
            Organization organization = opt.get();
            return Optional.of(new OrganizationDto(organization.getId(),
                    organization.getOrganizationName(),
                    organization.getPhoneNumber(),
                    organization.getEmail()));
        }
        return Optional.empty();
    }

    public boolean existsByPhoneNumberAndEmailAndOrganizationName(String phoneNumber, String email, String organizationName) {
        return organizationRepository.existsOrganizationByPhoneNumberAndAndEmailAndOrganizationName(phoneNumber, email, organizationName);
    }

    public Optional<OrganizationDto> save(OrganizationCreateDto dto /*,MultipartFile multipartFile*/) {
        Organization organization = new Organization();
       /* if (multipartFile==null){
            organization.setOrgImage(null);
            organization.setImageType(null);
            organization.setImageName(null);
        }*/
        organization.setOrganizationName(dto.organizationName());
        organization.setPhoneNumber(dto.phoneNumber());
        organization.setEmail(dto.email());
        organization.setPassword(dto.password());
        Organization saved = organizationRepository.save(organization);
        OrganizationDto orgDto = new OrganizationDto(saved.getId(),
                saved.getOrganizationName(),
                saved.getPhoneNumber(),
                saved.getEmail()/*,
                saved.getOrgImage()!=null?saved.getOrgImage():null*/);
        return Optional.of(orgDto);
    }

    public boolean existsById(Long id) {
        return organizationRepository.existsById(id);
    }

    public Optional<OrganizationDto> updateOrganization(OrganizationCreateDto dto, Long id/*, MultipartFile multipartFile*/) {
        Optional<Organization> optional = organizationRepository.findById(id);
        Organization organization = optional.orElseThrow(() -> new EntityNotFoundException("Organization not found"+id));

        organization.setId(id);

        if (dto.organizationName() != null) {
            organization.setOrganizationName(dto.organizationName());
        }
        if (dto.phoneNumber() != null) {
            organization.setPhoneNumber(dto.phoneNumber());
        }
        if (dto.email() != null) {
            organization.setEmail(dto.email());
        }
        if (dto.password() != null) {
            organization.setPassword(dto.password());
        }
        /*if (!multipartFile.isEmpty()) {
            organization.setImageName(multipartFile.getOriginalFilename());
            organization.setImageType(multipartFile.getContentType());
            try {
                organization.setOrgImage(multipartFile.getBytes());

            }catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        Organization org = organizationRepository.save(organization);
        OrganizationDto orgDto = new OrganizationDto(
                org.getId(),
                org.getOrganizationName(),
                org.getPhoneNumber(),
                org.getEmail()/*,
                org.getOrgImage()!=null?org.getOrgImage():null*/);
        return Optional.of(orgDto);



    }
}
