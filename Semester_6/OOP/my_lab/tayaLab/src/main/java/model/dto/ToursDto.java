package model.dto;

import model.entity.Tour;

import java.util.List;

public class ToursDto {
    private List<Tour> tours;

    public ToursDto(List<Tour> tours) {
        this.tours = tours;
    }
}
