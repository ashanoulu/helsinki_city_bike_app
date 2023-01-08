//package com.ashan.demo;
//
//import com.ashan.demo.controller.JourneyController;
//import com.ashan.demo.model.Journey;
//import com.ashan.demo.service.JourneyService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(JourneyController.class)
//public class JourneyControllerTest {
//    //    @Autowired
//    private MockMvc mockMvc;
//    //    @Autowired
//    ObjectMapper mapper;
//
//    @MockBean
//    private JourneyService journeyService;
//    @Test
//    public void getAllRecords_success() throws Exception {
//        this.mockMvc.perform(get("/station/541")).andDo(print()).andExpect(status().isOk());
//
////        Page<Journey> journey = null;
////
////        Pageable pageable = PageRequest.of(0, 20, Sort.by("id").descending());
////        Mockito.when(journeyService.all("al", "", pageable)).thenReturn(null);
////        mockMvc.perform(MockMvcRequestBuilders
////                        .get("/patient")
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk());
//    }
//
//}
