package com.cognizant.garage.data.repository;

import com.cognizant.garage.data.entity.WarehouseCarLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseCarLocationRepository extends JpaRepository<WarehouseCarLocation, Integer > {

    @Query("select a from WarehouseCarLocation a left join a.warehouseId left join a.carLocationId " +
            "where a.warehouseId.id = :warehouseId and a.carLocationId.id = :carLocationId")
    Optional<WarehouseCarLocation> findByWarehouseIdAndCarLocationId(Integer warehouseId, Integer carLocationId);
}
