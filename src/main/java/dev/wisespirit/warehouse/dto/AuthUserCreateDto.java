package dev.wisespirit.warehouse.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthUserCreateDto(String organizationName,String phoneNumber, String email,String password) {

}
