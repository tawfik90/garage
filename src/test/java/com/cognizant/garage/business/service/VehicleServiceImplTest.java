package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.requests.VehicleRequest;
import com.cognizant.garage.data.repository.CarLocationRepository;
import com.cognizant.garage.data.repository.MakeRepository;
import com.cognizant.garage.data.repository.ModelRepository;
import com.cognizant.garage.data.repository.VehicleRepository;
import com.cognizant.garage.data.repository.WarehouseCarLocationRepository;
import com.cognizant.garage.data.repository.WarehouseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VehicleServiceImplTest {

    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private MakeRepository makeRepository;
    @Mock
    private ModelRepository modelRepository;
    @Mock
    private CarLocationRepository carLocationRepository;
    @Mock
    private WarehouseCarLocationRepository warehouseCarLocationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vehicleService = new VehicleServiceImpl(vehicleRepository,
                warehouseRepository,
                makeRepository,
                modelRepository,
                carLocationRepository,
                warehouseCarLocationRepository);
    }
    @Test
    public void addVehicle() {

//        vehicleRequest = new VehicleRequest();
    }

    @Test
    public void updateVehicle() {

    }

    @Test
    public void deleteVehicle() {

    }
}