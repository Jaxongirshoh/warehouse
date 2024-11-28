package dev.wisespirit.warehouse.dto.auth;

import lombok.Setter;


public record AuthUserDto(Long id,String name,String surname, String phoneNumber,Long organizationId) {

}
