package dev.wisespirit.warehouse.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an authentication role within the system.
 *
 *  This class extends `BaseAuditable` and provides additional fields for defining roles and their associated permissions:
 *  - **name:** The unique name of the role.
 *  - **description:** A description of the role's purpose.
 *  - **permissions:** A list of permissions associated with the role.
 *
 *  @author wisespirit
 *  @version 0.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auth_roles")
public class AuthRole extends BaseAuditable{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToMany
    @JoinTable(
            name = "auth_roles_permissions",
            joinColumns = @JoinColumn(name = "auth_role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_permission_id",referencedColumnName = "id")
    )
    private List<AuthPermission> permissions = new ArrayList<>();
}
