package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.PickingJob;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PickingJobLineRepository extends CrudRepository<PickingJob, Long> {
}
