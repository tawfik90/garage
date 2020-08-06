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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "vehicle_auto")
    private Integer id;
    @Column(name = "year_model")
    private Integer yearModel;
    private double price;
    private boolean licensed;
    @Column(name = "date_added")
    private LocalDate dateAdded;

    @JoinColumn(name = "wcl_id", referencedColumnName = "id")
    @ManyToOne
    private WarehouseCarLocation warehouseCarLocation;

    @JoinColumn(name = "make_id", referencedColumnName = "id")
    @ManyToOne
    private Make makeId;
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @ManyToOne
    private Model modelId;
}
