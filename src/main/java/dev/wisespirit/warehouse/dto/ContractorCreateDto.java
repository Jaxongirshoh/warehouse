package dev.wisespirit.warehouse.dto;

import dev.wisespirit.warehouse.enums.ContractorType;

public record ContractorCreateDto(String name,
                                  String phoneNumber,
                                  String email,
                                  ContractorType contractorType,
                                  String serviceName) {
}
