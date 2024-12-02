package dev.wisespirit.warehouse.dto.auth;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record AuthUserCreateDto(@Column(nullable = false)String name,
                                @Column(nullable = false)String surname,
                                @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$") @Column(nullable = false,unique = true)String phoneNumber,
                                @Column(nullable = false)String password,
                                @Column(nullable = false)Long organizationId) {

}
