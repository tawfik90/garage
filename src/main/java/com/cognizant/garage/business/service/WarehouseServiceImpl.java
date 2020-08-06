package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.requests.WarehouseRequest;
import com.cognizant.garage.business.domain.response.WarehouseResponse;
import com.cognizant.garage.data.entity.Warehouse;
import com.cognizant.garage.data.repository.WarehouseRepository;
import com.cognizant.garage.exception.NotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    final private WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<WarehouseResponse> getAllWarehousesCars() throws Exception {
        TypeReference<List<WarehouseResponse>> warehouses = new TypeReference<List<WarehouseResponse>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/mockData.json");
        return new ObjectMapper().readValue(inputStream, warehouses);
    }

    @Override
    public Warehouse saveWarehouse(WarehouseRequest warehouseRequest) {
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
