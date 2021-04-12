package ua.taya.tayalab_boot.service;

import ua.taya.tayalab_boot.dto.ResponseDto;
import ua.taya.tayalab_boot.dto.ToursDto;

public interface TourService {

    ResponseDto updateHot(Long tourId);

    ToursDto getAllTours();
}
