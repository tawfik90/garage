package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.LocationDTO;
import com.cognizant.garage.business.domain.requests.WarehouseRequest;
import com.cognizant.garage.business.domain.response.WarehouseResponse;
import com.cognizant.garage.data.entity.Warehouse;
import com.cognizant.garage.data.repository.WarehouseRepository;
import com.cognizant.garage.exception.NotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class WarehouseServiceImplTest {

    private WarehouseServiceImpl warehouseService;

    @Mock
    private WarehouseRepository warehouseRepository;

    private JacksonTester<List<WarehouseResponse>> wrListJacksonTester;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
        warehouseService = new WarehouseServiceImpl(warehouseRepository);
    }

    @Test
    public void getAllWarehousesCars_ShouldReturnListOfCarsWrappedByWarehouse() throws Exception  {
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/mockData.json");
        List<WarehouseResponse> warehouseResponses = wrListJacksonTester.readObject(inputStream);

        List<WarehouseResponse> output = warehouseService.getAllWarehousesCars();

        assertThat(output).isEqualTo(warehouseResponses);
    }

    @Test
    public void saveWarehouse() throws Exception {
        WarehouseRequest warehouseRequest = new WarehouseRequest("Warehouse A", new LocationDTO("221,334444","22,333355"));
        Warehouse warehouse = Warehouse.builder().id(1)
                .locationLat(warehouseRequest.getLocation().getLatitude())
                .locationLong(warehouseRequest.getLocation().getLongitude())
                .name(warehouseRequest.getName())
                .build();
        given(warehouseRepository.save(any(Warehouse.class))).willReturn(warehouse);

        Warehouse result = warehouseService.saveWarehouse(warehouseRequest);

        verify(warehouseRepository).save(any(Warehouse.class));
        assertThat(result).isEqualTo(warehouse);
    }

    @Test
    public void updateWarehouse() throws Exception {
        Integer id = 1;
        WarehouseRequest warehouseRequest = new WarehouseRequest("Warehouse A", new LocationDTO("221,334445","22,333355"));
        Warehouse warehouse = Warehouse.builder().id(1)
                .locationLat(warehouseRequest.getLocation().getLatitude())
                .locationLong(warehouseRequest.getLocation().getLongitude())
                .name(warehouseRequest.getName())
                .build();
        given(warehouseRepository.findById(id)).willReturn(Optional.of(warehouse));
        given(warehouseRepository.save(warehouse)).willReturn(warehouse);

        Warehouse result = warehouseService.updateWarehouse(id, warehouseRequest);

        verify(warehouseRepository).save(any(Warehouse.class));
        assertThat(result).isEqualTo(warehouse);
    }

    @Test(expected = NotFoundException.class)
    public void updateWarehouse_shouldThrowNotFoundException() throws Exception {
        Integer id = 2;
        WarehouseRequest warehouseRequest = new WarehouseRequest("Warehouse A", new LocationDTO("221,334444","22,333355"));
        given(warehouseRepository.findById(id)).willReturn(Optional.empty());

        warehouseService.updateWarehouse(id, warehouseRequest);
    }

    @Test
    public void deleteWarehouse() throws Exception {
        Integer id = 1;

        warehouseService.deleteWarehouse(id);

        verify(warehouseRepository).deleteById(id);
    }
}