package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.response.WarehouseResponse;

import java.util.List;

public interface WarehouseService {

    List<WarehouseResponse> getAllWarehousesCars() throws Exception;

}
