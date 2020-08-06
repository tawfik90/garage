package com.cognizant.garage.web.service;

import com.cognizant.garage.business.domain.requests.VehicleRequest;
import com.cognizant.garage.business.service.VehicleService;
import com.cognizant.garage.data.entity.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class VehicleServiceController {

    final private VehicleService vehicleService;

    public VehicleServiceController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/warehouse/{warehouseId}/vehicle")
    public ResponseEntity<Vehicle> addNewVehicleInWarehouse(@PathVariable Integer warehouseId,
                                                            @RequestBody VehicleRequest vehicleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.addVehicle(warehouseId, vehicleRequest));
    }

    @PutMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id,
                                                 @RequestBody VehicleRequest vehicleRequest) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleRequest));
    }

    @DeleteMapping("/vehicle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVehicle(@PathVariable Integer id) {
        vehicleService.deleteVehicle(id);
    }

}
