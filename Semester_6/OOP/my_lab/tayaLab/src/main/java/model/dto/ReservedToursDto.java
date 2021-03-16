package model.dto;

import java.util.List;

public class ReservedToursDto {
    private List<ReservedTourDto> reservedTours;

    public ReservedToursDto(List<ReservedTourDto> reservedTours) {
        this.reservedTours = reservedTours;
    }
}
