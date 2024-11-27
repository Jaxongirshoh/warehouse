package dev.wisespirit.warehouse.dto.auth;

import lombok.Setter;


public record OrganizationUpdateDto(Long id,String organizationName, String phoneNumber, String email,String password/*,byte[] orgImage*/) {
}
