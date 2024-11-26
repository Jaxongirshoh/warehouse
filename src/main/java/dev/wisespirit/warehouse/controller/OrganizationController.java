package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.OrganizationCreateDto;
import dev.wisespirit.warehouse.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveOrganization(@RequestBody OrganizationCreateDto dto,
                                              @RequestPart MultipartFile multipartFile){
        if (organizationService.existsByPhoneNumberAndEmailAndOrganizationName(dto.phoneNumber(),dto.email(),dto.organizationName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        organizationService.save(dto,multipartFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
