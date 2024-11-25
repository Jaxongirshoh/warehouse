package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.auth.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    boolean existsOrganizationByPhoneNumberAndAndEmailAndOrganizationName(String phoneNumber, String email, String organizationName);
}
