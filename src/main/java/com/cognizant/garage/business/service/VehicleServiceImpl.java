package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.requests.VehicleRequest;
import com.cognizant.garage.data.entity.CarLocation;
import com.cognizant.garage.data.entity.Make;
import com.cognizant.garage.data.entity.Model;
import com.cognizant.garage.data.entity.Vehicle;
import com.cognizant.garage.data.entity.Warehouse;
import com.cognizant.garage.data.entity.WarehouseCarLocation;
import com.cognizant.garage.data.repository.CarLocationRepository;
import com.cognizant.garage.data.repository.MakeRepository;
import com.cognizant.garage.data.repository.ModelRepository;
import com.cognizant.garage.data.repository.VehicleRepository;
import com.cognizant.garage.data.repository.WarehouseCarLocationRepository;
import com.cognizant.garage.data.repository.WarehouseRepository;
import com.cognizant.garage.exception.NotFoundException;
import com.cognizant.garage.exception.UnexpectedMoveException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VehicleServiceImpl implements VehicleService {

    final private VehicleRepository vehicleRepository;
    final private WarehouseRepository warehouseRepository;
    final private MakeRepository makeRepository;
    final private ModelRepository modelRepository;
    final private CarLocationRepository carLocationRepository;
    final private WarehouseCarLocationRepository warehouseCarLocationRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository,
                              WarehouseRepository warehouseRepository,
                              MakeRepository makeRepository,
                              ModelRepository modelRepository,
                              CarLocationRepository carLocationRepository,
                              WarehouseCarLocationRepository warehouseCarLocationRepository) {
        this.vehicleRepository = vehicleRepository;
        this.warehouseRepository = warehouseRepository;
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
        this.carLocationRepository = carLocationRepository;
        this.warehouseCarLocationRepository = warehouseCarLocationRepository;
    }

    @Override
    public Vehicle addVehicle(Integer warehouseId, VehicleRequest vehicleRequest) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new NotFoundException("This warehouse is no found"));

        CarLocation carLocation = loadCarLocation(vehicleRequest.getLocation());

        WarehouseCarLocation warehouseCarLocation = loadWarehouseCarLocation(warehouse, carLocation);

        return vehicleRepository.save(Vehicle.builder().yearModel(vehicleRequest.getYearModel())
                .price(vehicleRequest.getPrice())
                .licensed(vehicleRequest.getLicensed())
                .dateAdded(LocalDate.now())
                .warehouseCarLocation(warehouseCarLocation)
                .makeId(loadMake(vehicleRequest.getMake()))
                .modelId(loadModel(vehicleRequest.getModel()))
                .build());
    }

    @Override
    public Vehicle updateVehicle(Integer vehicleId, VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("This vehicle is not exist"));

        if (!vehicle.getWarehouseCarLocation().getCarLocationId().getName()
                .equals(vehicleRequest.getLocation())) {
            throw new UnexpectedMoveException("You can't move car from place to another this API don't support this move yet");
        }
        return vehicleRepository.save(Vehicle.builder()
                .id(vehicleId)
                .modelId(loadModel(vehicleRequest.getModel()))
                .makeId(loadMake(vehicleRequest.getMake()))
                .yearModel(vehicleRequest.getYearModel())
                .licensed(vehicleRequest.getLicensed())
                .price(vehicleRequest.getPrice())
                .build());
    }

    @Override
    public void deleteVehicle(Integer id) {
        vehicleRepository.deleteById(id);
    }

    private Model loadModel(String name) {
        return modelRepository.findModelByName(name)
                .orElseGet(() -> modelRepository.save(Model.builder().name(name).build()));
    }

    private Make loadMake(String name) {
        return makeRepository.findMakeByName(name)
                .orElseGet(() -> makeRepository.save(Make.builder().name(name).build()));
    }

    private CarLocation loadCarLocation(String name) {
        return carLocationRepository.findCarLocationByName(name)
                .orElseGet(() -> carLocationRepository.save(CarLocation.builder().name(name).build()));
    }

    private WarehouseCarLocation loadWarehouseCarLocation(Warehouse warehouse, CarLocation carLocation) {
        return warehouseCarLocationRepository
                .findByWarehouseIdAndCarLocationId(warehouse.getId(), carLocation.getId())
                .orElseGet(() -> warehouseCarLocationRepository.save(WarehouseCarLocation.builder()
                        .carLocationId(carLocation)
                        .warehouseId(warehouse)
                        .build()));
    }

}
