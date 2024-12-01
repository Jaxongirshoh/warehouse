package dev.wisespirit.warehouse.entity.auth;

import dev.wisespirit.warehouse.entity.Contractor;
import dev.wisespirit.warehouse.entity.Warehouse;
import dev.wisespirit.warehouse.enums.OrganizationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * This class demonstrate an organization entity.
 * This class extends `BaseAuditable` and provides additional fields for defining organization information,
 * employees, and warehouses:
 * - **organizationName:** The unique name of the organization.
 * - **phoneNumber:** The unique phone number of the organization, validated to ensure it's in a specific format.
 * - **email:** The unique email address of the organization, validated to ensure it's a valid email address.
 * - **password:** The password for the organization's account.
 * - **orgImage:** The logo image of the organization, stored as a byte array.
 * - **status:** The current status of the organization (active or inactive).
 * - **employees:** A set of {@link AuthUser} objects associated with the organization, representing its employees.
 * - **warehouses:** A set of {@link Warehouse} objects associated with the organization, representing its warehouses.
 *
 * @author wisespirit
 * @version 0.1
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization extends BaseAuditable{
    @Column(nullable = false,unique = true)
    @NotBlank
    private String organizationName;
    @Column(nullable = false,unique = true)
    @Pattern(regexp = "^(?:\\+?998|0)?(33|55|77|88|90|91|93|94|95|97|98|99)\\d{7}$",message = "phone number is invalid")
    private String phoneNumber;
    @Column(nullable = false,unique = true)
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n",message = "email is invalid")
    private String email;
    @Column(nullable = false)
    @NotBlank
    private String password;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] orgImage;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrganizationStatus status = OrganizationStatus.ACTIVE;
}
