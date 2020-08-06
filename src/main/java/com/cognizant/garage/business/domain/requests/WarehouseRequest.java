package com.cognizant.garage.business.domain.requests;

import com.cognizant.garage.business.domain.LocationDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@EqualsAndHashCode
@ToString
public class WarehouseRequest {
    final private String name;
    final private LocationDTO location;
}
