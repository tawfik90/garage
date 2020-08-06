package com.cognizant.garage.web.service;

import com.cognizant.garage.business.domain.LocationDTO;
import com.cognizant.garage.business.domain.requests.WarehouseRequest;
import com.cognizant.garage.business.domain.response.WarehouseResponse;
import com.cognizant.garage.business.service.MetricService;
import com.cognizant.garage.business.service.VehicleService;
import com.cognizant.garage.business.service.WarehouseService;
import com.cognizant.garage.data.entity.Warehouse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@RunWith(SpringRunner.class)
@WebMvcTest
public class WarehouseServiceControllerTest {
    @MockBean
    private WarehouseService warehouseService;

    @MockBean
    private VehicleService vehicleService;

    @MockBean
    private MetricService metricService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<List<WarehouseResponse>> warehouseJacksonTester;
    private JacksonTester<WarehouseRequest> warehouseRequestJacksonTester;

    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getAllWarehousesCars_shouldReturnStatusOk() throws Exception {
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/mockData.json");
        List<WarehouseResponse> warehouseResponseList = warehouseJacksonTester.readObject(inputStream);
        given(warehouseService.getAllWarehousesCars()).willReturn(warehouseResponseList);

        MockHttpServletResponse response = mvc.perform(
                get("/warehouses/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        verify(warehouseService).getAllWarehousesCars();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void saveWarehouse() throws Exception {
        WarehouseRequest warehouseRequest = new WarehouseRequest("Warehouse test", new LocationDTO("122333", "232323"));
        String jsonBody = warehouseRequestJacksonTester.write(warehouseRequest).getJson();
        given(warehouseService.addWarehouse(any(WarehouseRequest.class))).willReturn(any(Warehouse.class));

        MockHttpServletResponse response = mvc.perform(post("/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andReturn()
                .getResponse();

        verify(warehouseService).addWarehouse(any(WarehouseRequest.class));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void updateWarehouse() throws Exception {
        WarehouseRequest warehouseRequest = new WarehouseRequest("Warehouse test", new LocationDTO("122333", "232323"));
        Integer id = 1;
        String jsonBody = warehouseRequestJacksonTester.write(warehouseRequest).getJson();
        given(warehouseService.updateWarehouse(id, warehouseRequest)).willReturn(Warehouse.builder()
                .id(1)
                .name("Warehouse test")
                .locationLong("122333")
                .locationLat("232323")
                .build());

        MockHttpServletResponse response = mvc.perform(put("/warehouses/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andReturn()
                .getResponse();

        verify(warehouseService).updateWarehouse(id, warehouseRequest);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void deleteWarehouse() throws Exception{
        Integer id = 1;
        MockHttpServletResponse response = mvc.perform(delete("/warehouses/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        verify(warehouseService).deleteWarehouse(any(Integer.class));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}