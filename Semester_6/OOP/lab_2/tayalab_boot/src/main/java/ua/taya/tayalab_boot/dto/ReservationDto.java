package ua.taya.tayalab_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDto {
    private Long tourId;
    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
