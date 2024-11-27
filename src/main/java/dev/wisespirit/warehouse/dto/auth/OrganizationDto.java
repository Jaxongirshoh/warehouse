package dev.wisespirit.warehouse.dto.auth;

import lombok.Setter;


public record OrganizationDto(Long id,String organizationName, String phoneNumber, String email/*,byte[] orgImage*/) {
}
