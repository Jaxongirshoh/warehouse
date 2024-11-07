package dev.wisespirit.warehouse.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
 * - **organizationName:** The name of the organization the user belongs to.
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_user_roles",
            joinColumns = @JoinColumn(name = "auth_user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_role_id",referencedColumnName = "id")
    )
    private List<AuthRole> roles = new ArrayList<>();

}
