package com.cognizant.garage.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class WarehouseCarLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "wcl_auto")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouseId;
    @ManyToOne
    @JoinColumn(name = "car_Location_id", referencedColumnName = "id")
    private CarLocation carLocationId;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouseCarLocation")
    private List<Vehicle> vehicleList;

}
