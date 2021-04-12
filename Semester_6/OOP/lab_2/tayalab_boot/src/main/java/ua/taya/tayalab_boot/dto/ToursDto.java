package ua.taya.tayalab_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.taya.tayalab_boot.entity.Tour;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToursDto {
    private List<Tour> tours;
}
