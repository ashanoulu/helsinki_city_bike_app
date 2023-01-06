//package com.ashan.demo;
//
//import com.ashan.demo.controller.StationController;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(StationController.class)
//public class StationControllerTest {
//
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    void getStation() throws Exception {
//        RequestBuilder request = get("/station/541");
//        MvcResult result = mvc.perform(request).andReturn();
//        assertEquals(200, result.getResponse().getStatus());
//    }
//}
