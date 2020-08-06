package com.cognizant.garage.business.domain.response;

import com.cognizant.garage.business.domain.LocationDTO;
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
public class WarehouseResponse {

    final private String _id;
    final private String name;
    final private LocationDTO location;
    final private CarResponse cars;

}
