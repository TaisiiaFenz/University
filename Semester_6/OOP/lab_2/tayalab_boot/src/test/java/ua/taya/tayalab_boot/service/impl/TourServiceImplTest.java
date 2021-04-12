package ua.taya.tayalab_boot.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.taya.tayalab_boot.dto.ResponseDto;
import ua.taya.tayalab_boot.dto.ToursDto;
import ua.taya.tayalab_boot.entity.Tour;
import ua.taya.tayalab_boot.entity.TourType;
import ua.taya.tayalab_boot.repository.TourRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class TourServiceImplTest {
    private static final Long DEFAULT_ID = 1L;
    @Mock
    private TourRepository tourRepository;
    @InjectMocks
    private TourServiceImpl tourService;
    private Tour tour;

    @Before
    public void setUp() throws Exception {
        tour = Tour.builder()
                .id(DEFAULT_ID)
                .tourType(TourType.RELAXATION)
                .isLastMinuteTour(false)
                .country("Italy")
                .price(BigDecimal.valueOf(4520))
                .build();
    }

    @Test
    public void updateHot() {
        Mockito.when(tourRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(tour));
        tour.setIsLastMinuteTour(true);
        ResponseDto expectedResponse = new ResponseDto(true, "Tour is updated");
        ResponseDto actualResponse = tourService.updateHot(DEFAULT_ID);
        Mockito.verify(tourRepository, Mockito.times(1)).save(eq(tour));
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getAllTours() {
        List<Tour> expectedTours = Collections.singletonList(tour);
        Mockito.when(tourRepository.findAll()).thenReturn(expectedTours);
        ToursDto actualTours = tourService.getAllTours();
        assertEquals(new ToursDto(expectedTours), actualTours);
    }
}