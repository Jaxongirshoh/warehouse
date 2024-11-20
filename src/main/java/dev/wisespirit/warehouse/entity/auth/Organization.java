package dev.wisespirit.warehouse.entity.auth;

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
    @Lob
    private byte[] orgImage;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrganizationStatus status = OrganizationStatus.ACTIVE;
    @OneToMany(mappedBy = "organization", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<AuthUser> employees = new HashSet<>();
    @OneToMany(mappedBy = "organization", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Warehouse> warehouses = new HashSet<>();
}
