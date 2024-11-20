package dev.wisespirit.warehouse.entity.auth;

import dev.wisespirit.warehouse.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an authenticated user within the system.
 *
 * This class extends `BaseAuditable` and adds specific fields for authentication:
 * - **name:** The name of the user.
 * -**surname:** The surname of the user.
 * - **phoneNumber:** The user's phone number, validated against a specific format.
 * - **email:** The user's email address, validated against a standard email format.
 * - **password:** The user's password.
 * @author wisespirit
 * @version 0.1
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auth_users")
public class AuthUser extends BaseAuditable{
    @Column(nullable = false)
    @NotBlank(message = "name is required")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "surname is required")
    private String surname;
    @Column(nullable = false,unique = true)
    @NotBlank(message = "phone number is required")
    @Pattern(regexp = "^(?:\\+?998|0)?(33|55|77|88|90|91|93|94|95|97|98|99)\\d{7}$",message = "phone number is invalid")
    private String phoneNumber;
    @Column(nullable = false)
    @NotBlank(message = "password is required")
    private String password;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_user_roles",
            joinColumns = @JoinColumn(name = "auth_user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_role_id",referencedColumnName = "id")
    )
    private List<AuthRole> roles = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id",nullable = false)
    private Organization organization;

}
