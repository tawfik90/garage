package com.cognizant.garage.data.repository;

import com.cognizant.garage.data.entity.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MakeRepository extends JpaRepository<Make, Integer> {

    Optional<Make> findMakeByName(String name);

}
