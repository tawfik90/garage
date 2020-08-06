package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.requests.VehicleRequest;
import com.cognizant.garage.data.entity.Vehicle;

public interface VehicleService {

    Vehicle addVehicle(Integer warehouseId, VehicleRequest vehicleRequest);

    Vehicle updateVehicle(Integer vehicleId, VehicleRequest vehicleRequest);

    void deleteVehicle(Integer Id);

}
