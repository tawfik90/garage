package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.response.WarehouseResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Override
    public List<WarehouseResponse> getAllWarehousesCars() throws Exception {
        TypeReference<List<WarehouseResponse>> warehouses = new TypeReference<List<WarehouseResponse>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/mockData.json");
        return new ObjectMapper().readValue(inputStream, warehouses);
    }
}
