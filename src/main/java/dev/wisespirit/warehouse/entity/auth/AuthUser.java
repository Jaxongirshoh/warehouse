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
import java.util.UUID;

/**
 * This class represents an authenticated user within the system.
 *
 * This class extends `BaseAuditable` and adds specific fields for authentication:
 * - **name:** The name of the user.
 * -**surname:** The surname of the user.
 * - **phoneNumber:** The user's phone number, validated against a specific format.
 * - **email:** The user's email address, validated against a standard email format.
 * - **password:** The user's password.
 * - **status:** The user's status.
 * -**organizationId** The Organization which a User work for
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Pattern(regexp = "^\\+(998)(9[0-9]|5[0]|8[8]|7[7]|3[3]|2[0])[0-9]{7}$")
    private String phoneNumber;
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
    private Long organizationId;

}
