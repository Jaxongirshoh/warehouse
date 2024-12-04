package dev.wisespirit.warehouse.dto.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;

public record WarehouseDto(String name,
                           @Column(nullable = false) String address,
                           @Column(nullable = false)
                           @Pattern(regexp = "^\\+(998)(9[0-9]|5[0]|8[8]|7[7]|3[3]|2[0])[0-9]{7}$")
                           String phone,
                           Long organizationId) {
}
