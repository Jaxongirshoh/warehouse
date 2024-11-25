package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.PickingJob;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PickingJobRepository extends CrudRepository<PickingJob, Long> {
    @Query(value = "SELECT * FROM picking_job pj\n" +
            "WHERE pj.status = 0 ORDER BY pj.date", nativeQuery = true)
        List<PickingJob> findAllPending();
}
