package dev.wisespirit.warehouse.dto;



public record OrganizationCreateDto(String organizationName, String phoneNumber, String email,String password,byte[] orgImage) {
}
