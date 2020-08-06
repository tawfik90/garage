package com.cognizant.garage.web.service;

import com.cognizant.garage.business.domain.requests.WarehouseRequest;
import com.cognizant.garage.business.service.WarehouseService;
import com.cognizant.garage.data.entity.Warehouse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping
    public ResponseEntity<Warehouse> saveWarehouse(@RequestBody WarehouseRequest warehouseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseService.saveWarehouse(warehouseRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Integer id,
                                                     @RequestBody WarehouseRequest warehouseRequest) {
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, warehouseRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWarehouse(@PathVariable Integer id) {
        warehouseService.deleteWarehouse(id);
    }

}
