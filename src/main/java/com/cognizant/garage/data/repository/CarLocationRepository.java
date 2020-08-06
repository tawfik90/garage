package com.cognizant.garage.data.repository;

import com.cognizant.garage.data.entity.CarLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarLocationRepository extends JpaRepository<CarLocation, Integer> {

    Optional<CarLocation> findCarLocationByName(String name);

}
