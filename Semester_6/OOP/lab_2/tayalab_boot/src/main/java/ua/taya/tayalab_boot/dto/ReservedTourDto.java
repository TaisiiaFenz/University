package ua.taya.tayalab_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.taya.tayalab_boot.entity.Client;
import ua.taya.tayalab_boot.entity.Status;
import ua.taya.tayalab_boot.entity.Tour;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservedTourDto {
    private Long reservationId;
    private Client client;
    private Tour tour;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
}
