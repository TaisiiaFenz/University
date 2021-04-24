package ua.taya.tayalab_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservedToursDto {
    private List<ReservedTourDto> reservedTours;
}