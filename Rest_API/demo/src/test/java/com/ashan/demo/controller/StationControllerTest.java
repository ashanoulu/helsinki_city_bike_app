package com.ashan.demo.controller;

import com.ashan.demo.DemoApplication;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StationControllerTest {

    private MockMvc mockMvc;
    private static String stationId;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void allTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stations")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void a_saveTest() throws Exception {
        String requestBody = "{\n" +
                "    \"nimi\": \"Test1\",\n" +
                "    \"namn\": \"Test1\",\n" +
                "    \"name\": \"Test1\",\n" +
                "    \"osoite\": \"Test1- Address\",\n" +
                "    \"address\": \"Test1- Address\",\n" +
                "    \"city\": \"Test1- city\",\n" +
                "    \"stad\": \"Test1- Address\",\n" +
                "    \"capacity\": 22,\n" +
                "    \"coordinateX\": \"23.343434\",\n" +
                "    \"coordinateY\": \"23.323323\"\n" +
                "}"; // JSON body

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/station")
                        .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(result.getResponse().getContentAsString());

        stationId = responseJson.get("stationId").asText();
    }

    @Test
    public void c_updateTest() throws Exception {
        String requestBody = "{\n" +
                "    \"nimi\": \"Test1\",\n" +
                "    \"namn\": \"Test1\",\n" +
                "    \"name\": \"Test1\",\n" +
                "    \"osoite\": \"Test1- Address\",\n" +
                "    \"address\": \"Test1- Address\",\n" +
                "    \"city\": \"Test1- city\",\n" +
                "    \"stad\": \"Test1- Address\",\n" +
                "    \"capacity\": 22,\n" +
                "    \"coordinateX\": \"23.343434\",\n" +
                "    \"coordinateY\": \"23.323323\"\n" +
                "}"; // JSON body

        mockMvc.perform(MockMvcRequestBuilders.put("/station/{stationId}", stationId)
                        .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void b_getStationByIdTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/station/{stationId}", stationId)
                        .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stationId").value(stationId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void d_deleteTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/station/{stationId}", stationId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());
    }
}
