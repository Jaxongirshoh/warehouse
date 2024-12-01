package dev.wisespirit.warehouse.dto.auth;

import jakarta.persistence.Column;
import lombok.Setter;


public record OrganizationCreateDto(@Column(nullable = false,unique = true) String organizationName,
                                    @Column(nullable = false,unique = true)String phoneNumber,
                                    @Column(nullable = false,unique = true)String email,
                                    @Column(nullable = false)String password) {
}
