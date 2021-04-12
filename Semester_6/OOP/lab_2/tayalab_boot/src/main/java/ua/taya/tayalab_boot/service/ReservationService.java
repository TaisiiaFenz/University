package ua.taya.tayalab_boot.service;

import ua.taya.tayalab_boot.dto.BillingDto;
import ua.taya.tayalab_boot.dto.ReservationDto;
import ua.taya.tayalab_boot.dto.ReservedToursDto;
import ua.taya.tayalab_boot.dto.ResponseDto;

public interface ReservationService {
    ReservedToursDto getByUserName(String userName);

    ResponseDto saveReservation(ReservationDto reservationDto);

    ResponseDto cancelReservation(Long reservationId);

    ReservedToursDto getAllReservations();

    ResponseDto acceptReservation(BillingDto billingDto);
}
