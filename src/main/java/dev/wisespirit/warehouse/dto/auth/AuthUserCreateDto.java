package dev.wisespirit.warehouse.dto.auth;


import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record AuthUserCreateDto(@Column(nullable = false)String name,
                                @Column(nullable = false)String surname,
                                @Column(nullable = false,unique = true)String phoneNumber,
                                @Column(nullable = false,unique = true)String email,
                                @Column(nullable = false)String password,
                                @Column(nullable = false)Long organizationId) {

}
