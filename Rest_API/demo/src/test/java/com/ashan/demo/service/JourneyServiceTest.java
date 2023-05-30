package com.ashan.demo.service;

import com.ashan.demo.model.Journey;
import com.ashan.demo.repository.JourneyRepository;
import com.ashan.demo.service.impl.JourneyServiceImpl;
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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JourneyServiceTest {
    @Mock
    private JourneyRepository journeyRepository;

    @InjectMocks
    private JourneyServiceImpl journeyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void allTest() {

        String dateString = "2023-05-28 12:34:56";
        String pattern = "yyyy-MM-dd HH:mm:ss";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            java.util.Date parsedDate = dateFormat.parse(dateString);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            List<Journey> journey = new ArrayList<Journey>();
            journey.add(new Journey(1L, timestamp, timestamp, "a2", "al", "qwer", "qwer", 20, 300));
            journey.add(new Journey(2L, timestamp, timestamp, "al", "al", "qwer", "qwer", 20, 300));
            journey.add(new Journey(3L, timestamp, timestamp, "ww", "al", "qwer", "qwer", 20, 300));
            journey.add(new Journey(4L, timestamp, timestamp, "w2qw", "al", "qwer", "qwer", 20, 300));
            journey.add(new Journey(5L, timestamp, timestamp, "w4w", "al", "qwer", "qwer", 20, 300));

            Pageable pageable = PageRequest.of(0, 10, getSortDirection("departureStationName", "DESC"));
            Page<Journey> page = new PageImpl<Journey>(journey, pageable, journey.size());


            Mockito.when(journeyRepository.findAllByDepartureStationNameContainsAndAndReturnStationNameContaining("al", "", pageable)).thenReturn(page);
            Page<Journey> testJourneyListPageable = journeyService.all("al", "", pageable);
            Mockito.verify(journeyRepository).findAllByDepartureStationNameContainsAndAndReturnStationNameContaining("al", "", pageable);

            assertNotNull(testJourneyListPageable);
            assertEquals(5, testJourneyListPageable.getTotalElements());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void countByDepartureStationIdTest() {

        try {
            Mockito.when(journeyRepository.countByDepartureStationId("100")).thenReturn(3);

            int serviceResult = journeyService.countByDepartureStationId("al");
            Mockito.verify(journeyRepository).countByDepartureStationId("al");

            assertNotNull(serviceResult);
            assertEquals(0, serviceResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void countByReturnStationIdIdTest() {

        try {
            Mockito.when(journeyRepository.countByReturnStationId("23")).thenReturn(30);

            int result = journeyService.countByReturnStationId("23");
            Mockito.verify(journeyRepository).countByReturnStationId("23");

            assertNotNull(result);
            assertEquals(30, result);

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
