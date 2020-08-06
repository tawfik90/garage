package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.requests.WarehouseRequest;
import com.cognizant.garage.business.domain.response.WarehouseResponse;
import com.cognizant.garage.data.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<WarehouseResponse> getAllWarehousesCars() throws Exception;

    Warehouse addWarehouse(WarehouseRequest warehouseRequest);

    Warehouse updateWarehouse(Integer id, WarehouseRequest warehouseRequest);

    void deleteWarehouse(Integer id);

}
