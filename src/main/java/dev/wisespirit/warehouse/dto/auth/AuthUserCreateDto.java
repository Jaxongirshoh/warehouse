package dev.wisespirit.warehouse.dto.auth;


import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
public record AuthUserCreateDto(String name,String surname,String phoneNumber, String email,String password) {

}
