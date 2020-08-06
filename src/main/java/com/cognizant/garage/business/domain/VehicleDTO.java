package com.cognizant.garage.business.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class VehicleDTO {

    final private String make;
    final private String model;
    @JsonProperty(value = "year_model")
    final private Integer yearModel;
    final private Double price;
    final private Boolean licensed;


}
