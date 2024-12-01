package dev.wisespirit.warehouse.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents an authentication permission within the system.
 *
 *   This class extends `BaseAuditable` and provides fields for defining permissions:
 *   - **name:** The unique name of the permission.
 *   - **description:** A description of the permission's purpose.
 *
 * @author wisespirit
 * @version 0.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auth_permissions")
public class AuthPermission extends BaseAuditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
}
