package com.cognizant.garage.data.repository;

import com.cognizant.garage.data.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {

    Optional<Model> findModelByName(String Name);

}
