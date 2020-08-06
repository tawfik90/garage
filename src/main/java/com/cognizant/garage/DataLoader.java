package com.cognizant.garage;

import com.cognizant.garage.business.domain.requests.VehicleRequest;
import com.cognizant.garage.business.domain.requests.WarehouseRequest;
import com.cognizant.garage.business.domain.response.WarehouseResponse;
import com.cognizant.garage.business.service.VehicleService;
import com.cognizant.garage.business.service.WarehouseService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@Profile("dev")
public class DataLoader implements ApplicationRunner {

    final private WarehouseService warehouseService;
    final private VehicleService vehicleService;

    public DataLoader(WarehouseService warehouseService, VehicleService vehicleService) {
        this.warehouseService = warehouseService;
        this.vehicleService = vehicleService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<WarehouseResponse>> typeReference = new TypeReference<List<WarehouseResponse>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/mockData.json");
        List<WarehouseResponse> warehouseResponsesList = mapper.readValue(inputStream, typeReference);
        warehouseResponsesList.forEach(warehouse -> {
            warehouseService.addWarehouse(new WarehouseRequest(warehouse.getName(), warehouse.getLocation()));
            warehouse.getCars().getVehicles().forEach(vehicle -> {
                VehicleRequest vehicleRequest = new VehicleRequest(vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getYearModel(),
                        vehicle.getPrice(),
                        vehicle.getLicensed(),
                        warehouse.getCars().getLocation());
                vehicleService.addVehicle(new Integer(warehouse.get_id()), vehicleRequest);
            });

        });
    }
}
