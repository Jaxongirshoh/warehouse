package dev.wisespirit.warehouse.entity;

import dev.wisespirit.warehouse.entity.auth.BaseAuditable;
import dev.wisespirit.warehouse.entity.auth.Organization;
import dev.wisespirit.warehouse.enums.ContractorType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a contractor within the system.

 * This class extends `BaseAuditable` and provides fields for defining contractor details:
 * - **name:** The name of the contractor.
 * - **phoneNumber:** The phone number of the contractor, validated against a specific format.
 * - **email:** The email address of the contractor, validated against a standard email format.
 * - **type:** The type of contractor, enumerated as `ContractorType`.
 * - **serviceName:** The service provided by the contractor.
 *
 * @author wisespirit
 * @version 0.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contractors")
public class Contractor extends BaseAuditable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    @Pattern(regexp = "^(?:\\+?998|0)?(33|55|77|88|90|91|93|94|95|97|98|99)\\d{7}$",message = "phone number is invalid")
    private String phoneNumber;
    @Column(nullable = false,unique = true)
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n",message = "email is invalid")
    private String email;
    @Enumerated(EnumType.STRING)
    private ContractorType type;
    @Column(nullable = false)
    private String serviceName;
    @ManyToOne
    @JoinColumn(name = "organization_id",nullable = false)
    private Organization organization;
}
