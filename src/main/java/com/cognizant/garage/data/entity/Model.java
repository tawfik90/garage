package com.cognizant.garage.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "model_auto")
    private Integer id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "modelId")
    private List<Vehicle> vehicleList;
}

