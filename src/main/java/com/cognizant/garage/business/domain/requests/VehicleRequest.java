package com.cognizant.garage.business.domain.requests;

import com.cognizant.garage.business.domain.VehicleDTO;
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

}
