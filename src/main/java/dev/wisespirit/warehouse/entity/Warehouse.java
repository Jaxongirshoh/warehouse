package dev.wisespirit.warehouse.entity;

import dev.wisespirit.warehouse.entity.auth.BaseAuditable;
import dev.wisespirit.warehouse.entity.auth.Organization;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

/**
 * This class represents a warehouse within the system/organization.
 * This class extends `BaseAuditable` and provides fields for defining warehouse details:
 * - **name:** The name of the warehouse.
 * - **address:** The address of the warehouse.
 * - **phone:** The phone number of the warehouse, validated against a specific format.
 *
 * @author wisespirit
 * @version 0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehouses")
public class Warehouse extends BaseAuditable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}\\n\",message = \"email is invalid")
    private String phone;
    private Long organizationId;
}

