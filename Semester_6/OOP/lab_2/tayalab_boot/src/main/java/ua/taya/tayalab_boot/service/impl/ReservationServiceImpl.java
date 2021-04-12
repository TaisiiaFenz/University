package ua.taya.tayalab_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.taya.tayalab_boot.dto.BillingDto;
import ua.taya.tayalab_boot.dto.ReservationDto;
import ua.taya.tayalab_boot.dto.ReservedToursDto;
import ua.taya.tayalab_boot.dto.ResponseDto;
import ua.taya.tayalab_boot.entity.*;
import ua.taya.tayalab_boot.mapper.BillingMapper;
import ua.taya.tayalab_boot.mapper.ReservationMapper;
import ua.taya.tayalab_boot.repository.BillingRepository;
import ua.taya.tayalab_boot.repository.ReservationRepository;
import ua.taya.tayalab_boot.repository.UserRepository;
import ua.taya.tayalab_boot.service.ClientService;
import ua.taya.tayalab_boot.service.ReservationService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ClientService clientService;
    private final ReservationRepository reservationRepository;
    private final BillingRepository billingRepository;
    private final UserRepository userRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private BillingMapper billingMapper;

    @Override
    public ReservedToursDto getByUserName(String userName) {
        Long userId = userRepository.findByUsername(userName).orElseThrow(()->new IllegalArgumentException("No user with username: " + userName)).getId();
        Client client = clientService.findClientByUserId(userId);
        List<Reservation> reservations = reservationRepository.findByClient(client);
        return new ReservedToursDto(reservationMapper.reservationListToReservedTourList(reservations));
    }

    @Override
    public ResponseDto saveReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.dtoToEntity(reservationDto);
        reservation.setClient(clientService.findClientByUserId(reservationDto.getUserId()));
        reservation.setReservationDate(LocalDate.now());
        reservation.setStatus(Status.NEW);
        reservationRepository.save(reservation);
        return new ResponseDto(true, "Reservation was made successfully");
    }

    @Override
    public ResponseDto cancelReservation(Long reservationId) {
        try {
            Reservation reservation = findReservationById(reservationId);
            reservation.setStatus(Status.NOT_ACCEPTED);
            reservationRepository.save(reservation);
            return new ResponseDto(true, "Reservation is cancelled");
        } catch (IllegalArgumentException e) {
            return new ResponseDto(false, e.getMessage());
        }
    }

    private Reservation findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("No reservation with id: " + reservationId));
    }

    @Override
    public ReservedToursDto getAllReservations() {
        return new ReservedToursDto(
                reservationMapper.reservationListToReservedTourList(reservationRepository.findAll())
        );
    }

    @Override
    public ResponseDto acceptReservation(BillingDto billingDto) {
        try {
            Reservation reservation = findReservationById(billingDto.getReservationId());
            reservation.setStatus(Status.ACCEPTED);
            reservationRepository.save(reservation);

            Billing billing = billingMapper.billingFromDto(billingDto);
            billing.setSum(billing.getReservation().getTour().getPrice().multiply(BigDecimal.ONE.subtract((BigDecimal.valueOf(billing.getDiscount().getDiscountPercentage()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)))).setScale(2, RoundingMode.HALF_UP));
            billingRepository.save(billing);
            return new ResponseDto(true, "Reservation is accepted. Billing is saved");
        } catch (IllegalArgumentException e) {
            return new ResponseDto(false, e.getMessage());
        }
    }
}
