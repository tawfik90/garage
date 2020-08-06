package com.cognizant.garage.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WarehouseCarLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "wcl_auto")
    private Integer wclId;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouseId;
    @ManyToOne
    @JoinColumn(name = "car_Location_id", referencedColumnName = "id")
    private CarLocation carLocationId;
}
