package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.LocationDTO;
import com.cognizant.garage.business.domain.requests.WarehouseRequest;
import com.cognizant.garage.business.domain.response.CarResponse;
import com.cognizant.garage.business.domain.response.VehicleResponse;
import com.cognizant.garage.business.domain.response.WarehouseResponse;
import com.cognizant.garage.data.entity.Warehouse;
import com.cognizant.garage.data.entity.WarehouseCarLocation;
import com.cognizant.garage.data.repository.WarehouseCarLocationRepository;
import com.cognizant.garage.data.repository.WarehouseRepository;
import com.cognizant.garage.exception.NotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    final private WarehouseRepository warehouseRepository;
    final private WarehouseCarLocationRepository warehouseCarLocationRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseCarLocationRepository warehouseCarLocationRepository) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseCarLocationRepository = warehouseCarLocationRepository;
    }

    @Override
    public List<WarehouseResponse> getAllWarehousesCars() throws Exception {
        List<WarehouseCarLocation> warehouseCarLocations = warehouseCarLocationRepository.findAll();
        List<WarehouseResponse> warehouseResponseList = new ArrayList<>();
        warehouseCarLocations.forEach(warehouseCarLocation -> {
            List<VehicleResponse> vehicleResponses = new ArrayList<>();
            warehouseCarLocation.getVehicleList().forEach(vehicle -> {
                VehicleResponse vehicleResponse = new VehicleResponse(vehicle.getMakeId().getName(),
                        vehicle.getModelId().getName(),
                        vehicle.getYearModel(),
                        vehicle.getPrice(),
                        vehicle.isLicensed(),
                        vehicle.getId(),
                        vehicle.getDateAdded());
                vehicleResponses.add(vehicleResponse);
            });
            LocationDTO locationDTO = new LocationDTO(warehouseCarLocation.getWarehouseId().getLocationLat(),
                    warehouseCarLocation.getWarehouseId().getLocationLong());

            WarehouseResponse warehouseResponse =
                    new WarehouseResponse(warehouseCarLocation.getWarehouseId().getId().toString(),
                            warehouseCarLocation.getWarehouseId().getName(),
                            locationDTO,
                            new CarResponse(warehouseCarLocation.getCarLocationId().getName(), vehicleResponses));
            warehouseResponseList.add(warehouseResponse);
        });

        return warehouseResponseList;
    }

    @Override
    public Warehouse addWarehouse(WarehouseRequest warehouseRequest) {
        return warehouseRepository.save(Warehouse.builder()
                .name(warehouseRequest.getName())
                .locationLong(warehouseRequest.getLocation().getLongitude())
                .locationLat(warehouseRequest.getLocation().getLatitude())
                .build());
    }

    @Override
    public Warehouse updateWarehouse(Integer id, WarehouseRequest warehouseRequest) {
        warehouseRepository.findById(id).orElseThrow(() -> new NotFoundException("This warehouse is not found"));
        return warehouseRepository.save(Warehouse.builder()
                .id(id)
                .name(warehouseRequest.getName())
                .locationLat(warehouseRequest.getLocation().getLatitude())
                .locationLong(warehouseRequest.getLocation().getLongitude())
                .build());
    }

    @Override
    public void deleteWarehouse(Integer id) {
        warehouseRepository.delete(warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This warehouse is not found")));
    }
}
