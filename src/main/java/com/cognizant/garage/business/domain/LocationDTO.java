package com.cognizant.garage.business.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class LocationDTO {

    @JsonProperty(value = "lat")
    final private String latitude;

    @JsonProperty(value = "long")
    final private String longitude;

}
