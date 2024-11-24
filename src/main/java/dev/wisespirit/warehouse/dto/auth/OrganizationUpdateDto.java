package dev.wisespirit.warehouse.dto.auth;

import lombok.Setter;

@Setter
public record OrganizationUpdateDto(String organizationName, String phoneNumber, String email,String password,byte[] orgImage) {
}
