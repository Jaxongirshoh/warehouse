package dev.wisespirit.warehouse.dto.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.Setter;


public record OrganizationCreateDto(@Column(nullable = false,unique = true) String organizationName,
                                    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$")@Column(nullable = false,unique = true)String phoneNumber,
                                    @Pattern(
                                            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                                            message = "Invalid email format"
                                    )@Column(nullable = false,unique = true)String email,
                                    @Column(nullable = false)String password) {
}
