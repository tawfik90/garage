package com.cognizant.garage.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.nashorn.internal.ir.annotations.Ignore;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "warehouse_auto")
    private Integer id;
    @Column(unique = true)
    private String name;
    private String locationLat;
    private String locationLong;
    @JsonIgnore
    @OneToMany(mappedBy = "warehouseId")
    private List<WarehouseCarLocation> warehouseCarLocations;
}
