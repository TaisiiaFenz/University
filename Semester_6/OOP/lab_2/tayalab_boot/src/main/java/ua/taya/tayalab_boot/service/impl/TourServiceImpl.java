package ua.taya.tayalab_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.taya.tayalab_boot.dto.ResponseDto;
import ua.taya.tayalab_boot.dto.ToursDto;
import ua.taya.tayalab_boot.entity.Tour;
import ua.taya.tayalab_boot.repository.TourRepository;
import ua.taya.tayalab_boot.service.TourService;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;

    @Override
    public ResponseDto updateHot(Long tourId) {
        try {
            Tour tour = tourRepository.findById(tourId)
                    .orElseThrow(() -> new IllegalArgumentException("No tour with id: " + tourId));
            tour.setIsLastMinuteTour(!tour.getIsLastMinuteTour());
            tourRepository.save(tour);
            return new ResponseDto(true, "Tour is updated");
        } catch (IllegalArgumentException e) {
            return new ResponseDto(false, e.getMessage());
        }
    }

    @Override
    public ToursDto getAllTours() {
        return new ToursDto(tourRepository.findAll());
    }

}
