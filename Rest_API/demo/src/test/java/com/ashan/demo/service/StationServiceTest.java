package com.ashan.demo.service;

import com.ashan.demo.common.StationViewDTO;
import com.ashan.demo.model.Station;
import com.ashan.demo.repository.StationRepository;
import com.ashan.demo.service.impl.StationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StationServiceTest {

    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private StationServiceImpl stationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void allTest() {

        try {
            List<Station> stations = new ArrayList<Station>();
            stations.add(new Station(1L, "TEST1", "Test", "Test", "Test", "", "Address", "", "", "", 20, "", ""));
            stations.add(new Station(2L, "TEST2", "Test2", "Test", "Test", "", "Address", "", "", "", 20, "", ""));

            Pageable pageable = PageRequest.of(0, 10, getSortDirection("departureStationName", "DESC"));
            Page<Station> page = new PageImpl<Station>(stations, pageable, stations.size());


            Mockito.when(stationRepository.findAllByNimiContainsOrNamnContainsOrNameContains("Test","Test","Test", pageable)).thenReturn(page);
            Page<Station> stationsResults = stationService.all("Test", pageable);
            Mockito.verify(stationRepository).findAllByNimiContainsOrNamnContainsOrNameContains("Test","Test","Test", pageable);

            assertNotNull(stationsResults);
            assertEquals(2, stationsResults.getTotalElements());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByStationIdTest() {

        try {
            Station station = new Station(1L, "TEST1", "Test", "Test", "Test", "", "Address", "", "", "", 20, "", "");

            Mockito.when(stationRepository.findByStationId("Test1")).thenReturn(station);
            StationViewDTO stationResult = stationService.getStationById("Test1");
            Mockito.verify(stationRepository).findByStationId("Test1");

            assertNotNull(stationResult);
            assertEquals("TEST1", stationResult.getStationId());
            assertEquals("Address", stationResult.getStation().getAddress());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return the Sort object according to user parameters
    private Sort getSortDirection(String sort, String direction) {
        Sort data = Sort.by(sort).ascending();

        if (direction.equals("DESC")) {
            data = Sort.by(sort).descending();
        }
        return data;
    }

}
