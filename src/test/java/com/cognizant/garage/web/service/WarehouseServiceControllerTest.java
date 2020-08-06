package com.cognizant.garage.web.service;

import com.cognizant.garage.business.domain.response.WarehouseResponse;
import com.cognizant.garage.business.service.MetricService;
import com.cognizant.garage.business.service.WarehouseService;
import com.cognizant.garage.web.filter.MetricFilter;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest
public class WarehouseServiceControllerTest {
    @MockBean
    private WarehouseService warehouseService;
    

    @MockBean
    private MetricService metricService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<List<WarehouseResponse>> warehouseJacksonTester;

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
        assertThat(response.getContentAsString()).isEqualTo(warehouseJacksonTester.write(warehouseResponseList).getJson());
    }
}