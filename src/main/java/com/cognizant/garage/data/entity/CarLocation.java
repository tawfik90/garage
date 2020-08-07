package com.cognizant.garage.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class CarLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "car_location_auto")
    private Integer id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "carLocationId")
    private List<WarehouseCarLocation> warehouseCarLocations;
}
