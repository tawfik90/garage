package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.response.WarehouseResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class WarehouseServiceImplTest {

    private WarehouseServiceImpl warehouseService;

    private JacksonTester<List<WarehouseResponse>> wrListJacksonTester;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
        warehouseService = new WarehouseServiceImpl();
    }

    @Test
    public void getAllWarehousesCars_ShouldReturnListOfCarsWrappedByWarehouse() throws Exception  {
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/mockData.json");
        List<WarehouseResponse> warehouseResponses = wrListJacksonTester.readObject(inputStream);

        List<WarehouseResponse> output = warehouseService.getAllWarehousesCars();

        assertThat(output).isEqualTo(warehouseResponses);
    }
}