package dev.wisespirit.warehouse.dto;

public record OrganizationUpdateDto(String organizationName, String phoneNumber, String email,String password,byte[] orgImage) {
}
