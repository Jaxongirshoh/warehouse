package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.auth.*;
import dev.wisespirit.warehouse.service.AuthUserService;
import dev.wisespirit.warehouse.service.OrganizationService;
import dev.wisespirit.warehouse.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final AuthUserService authUserService;

    public OrganizationController(OrganizationService organizationService, AuthUserService authUserService) {
        this.organizationService = organizationService;
        this.authUserService = authUserService;
    }

    @PostMapping(value = "/register",consumes = "application/json")
    public ResponseEntity<ApiResponse> saveOrganization(@Valid @RequestBody OrganizationCreateDto dto, BindingResult bindingResult
                                             /* ,@RequestPart(required = false) MultipartFile multipartFile*/){
        if (bindingResult.hasErrors()) {
            // Create a map of field errors
            Map<String, String> fieldErrors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existingValue, newValue) -> existingValue
                    ));

            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("Validation failed", fieldErrors));
        }
        try {
            if (organizationService.existsByPhoneNumberAndEmailAndOrganizationName(dto.phoneNumber(),dto.email(),dto.organizationName())) {
                return new ResponseEntity<>(ApiResponse.error("bad credentials",HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
            }
            Optional<OrganizationDto> optional = organizationService.save(dto/*, multipartFile*/);
            if (optional.isPresent()){
                return new ResponseEntity<>(ApiResponse.success(true),HttpStatus.CREATED);
            }
            return new ResponseEntity<>(ApiResponse.error("something wrong",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error("something wrong",null), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrganization(@PathVariable Long id){
        Optional<OrganizationDto> optional = organizationService.findOrganizationById(id);
        if (optional.isPresent()){
            return new ResponseEntity<>(ApiResponse.success(optional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApiResponse.error("not found",null),HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateOrg(@RequestBody OrganizationUpdateDto dto,
                                                 @PathVariable Long id/*,
                                                 @RequestPart(required = false) MultipartFile multipartFile*/){
        if (organizationService.existsById(id)){
            Optional<OrganizationDto> optional = organizationService.updateOrganization(dto,id/*,multipartFile*/);
            if (optional.isPresent()){
                return new ResponseEntity<>(ApiResponse.success(optional.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ApiResponse.error("not found",null),HttpStatus.NOT_FOUND);
    }

    @PostMapping("/employee/create/{organizationId}")
    public ResponseEntity<ApiResponse> createEmployee(@RequestBody AuthUserCreateDto dto,
                                                      @PathVariable Long organizationId){
        if (authUserService.existsByPhoneNumber(dto.phoneNumber())){
            return new ResponseEntity<>(ApiResponse.error("bad credentials",HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
        }
        Optional<AuthUserDto> optional = authUserService.save(dto, organizationId);
        if (optional.isPresent()){
            return new ResponseEntity<>(ApiResponse.success(optional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApiResponse.error("something wrong",null),HttpStatus.BAD_REQUEST);
    }
}
