package com.cognizant.garage.business.domain.requests;

import com.cognizant.garage.business.domain.VehicleDTO;
import lombok.AllArgsConstructor;
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
@EqualsAndHashCode(callSuper = true)
public class VehicleRequest extends VehicleDTO {

    final private String location;

    public VehicleRequest(String make, String model, Integer yearModel, Double price, Boolean licensed, String location) {
        super(make, model, yearModel, price, licensed);
        this.location = location;
    }
}
