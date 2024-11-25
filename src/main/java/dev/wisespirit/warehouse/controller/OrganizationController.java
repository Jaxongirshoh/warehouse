package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.auth.OrganizationCreateDto;
import dev.wisespirit.warehouse.dto.auth.OrganizationDto;
import dev.wisespirit.warehouse.service.OrganizationService;
import dev.wisespirit.warehouse.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> saveOrganization(@RequestBody OrganizationCreateDto dto,
                                              @RequestPart MultipartFile multipartFile){
        if (organizationService.existsByPhoneNumberAndEmailAndOrganizationName(dto.phoneNumber(),dto.email(),dto.organizationName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Optional<OrganizationDto> optional = organizationService.save(dto, multipartFile);
        if (optional.isPresent()){
            return new ResponseEntity<>(ApiResponse.success(true),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ApiResponse(true,404), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganization(@PathVariable Long id){
        Optional<OrganizationDto> optional = organizationService.findOrganizationById(id);
        if (optional.isPresent()){
            return new ResponseEntity<>(ApiResponse.success(optional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateOrg(@RequestBody OrganizationCreateDto dto,
                                                 @PathVariable Long id,
                                                 @RequestPart(required = false) MultipartFile multipartFile){
        if (organizationService.existsById(id)){
            Optional<OrganizationDto> optional = organizationService.updateOrganization(dto,id,multipartFile);
            if (optional.isPresent()){
                return new ResponseEntity<>(ApiResponse.success(optional.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ApiResponse.error("failed in update",HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }
}
