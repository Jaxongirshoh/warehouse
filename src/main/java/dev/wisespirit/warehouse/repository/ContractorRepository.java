package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.dto.ContractorCreateDto;
import dev.wisespirit.warehouse.entity.Contractor;
import dev.wisespirit.warehouse.enums.ContractorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {

}
