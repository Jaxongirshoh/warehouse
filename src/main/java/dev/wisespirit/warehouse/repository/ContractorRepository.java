package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.dto.ContractorCreateDto;
import dev.wisespirit.warehouse.entity.Contractor;
import dev.wisespirit.warehouse.enums.ContractorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractorRepository extends JpaRepository<Contractor, UUID> {
    void updateById(UUID Id);
    void save(ContractorCreateDto contractorCreateDto);
    Optional<Contractor> findByPhoneNumber(String phoneNumber);
    Optional<Contractor> findByName(String name);
    Optional<Contractor> findByEmail(String email);
    List<Contractor> findAllByType(ContractorType contractorType);
    List<Contractor> findAll();

}
