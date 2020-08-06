package com.cognizant.garage.web.service;

import com.cognizant.garage.business.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/warehouses")
public class WarehouseServiceController {

    final private WarehouseService warehouseService;

    public WarehouseServiceController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getAllWarehousesCars() throws Exception{
        return ResponseEntity.ok(warehouseService.getAllWarehousesCars());
    }

}
