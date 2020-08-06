package com.cognizant.garage.business.domain.response;

import com.cognizant.garage.business.domain.VehicleDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class VehicleResponse extends VehicleDTO {

    final private Integer _id;
    @JsonProperty(value = "date_added")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    final private LocalDate dateAdded;

    public VehicleResponse(String make, String model, Integer yearModel, Double price, Boolean licensed, Integer _id, LocalDate dateAdded) {
        super(make, model, yearModel, price, licensed);
        this._id = _id;
        this.dateAdded = dateAdded;
    }

}
