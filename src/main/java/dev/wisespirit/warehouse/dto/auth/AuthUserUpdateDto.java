package dev.wisespirit.warehouse.dto.auth;


import lombok.Setter;


public record AuthUserUpdateDto(String name,String surname,String phoneNumber,String password) {
}
