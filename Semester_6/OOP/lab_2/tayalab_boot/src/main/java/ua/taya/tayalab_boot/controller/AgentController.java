package ua.taya.tayalab_boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.taya.tayalab_boot.dto.BillingDto;
import ua.taya.tayalab_boot.dto.ReservedToursDto;
import ua.taya.tayalab_boot.dto.ResponseDto;
import ua.taya.tayalab_boot.dto.SignUpDto;
import ua.taya.tayalab_boot.entity.Discount;
import ua.taya.tayalab_boot.service.ClientService;
import ua.taya.tayalab_boot.service.DiscountService;
import ua.taya.tayalab_boot.service.ReservationService;
import ua.taya.tayalab_boot.service.TourService;

import javax.management.openmbean.CompositeData;
import java.util.List;

@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
public class AgentController {
    private final TourService tourService;
    private final ReservationService reservationService;
    private final DiscountService discountService;
    private final ClientService clientService;

    @PutMapping("/update-hot-tour/{id}")
    public ResponseDto updateHotTour(@PathVariable("id") Long tourId) {
        return tourService.updateHot(tourId);
    }

    @PutMapping("/cancel-reservation?{id}")
    public ResponseDto cancelReservation(@PathVariable("id") Long reservationId) {
        return reservationService.cancelReservation(reservationId);
    }

    @PostMapping("/accept-reservation")
    public ResponseDto acceptReservation(@RequestBody BillingDto billingDto){
        return reservationService.acceptReservation(billingDto);
    }

    @GetMapping("/reserved-tours")
    public ReservedToursDto getAllReservations(){
        return reservationService.getAllReservations();
    }

    //TODO: it is better to wrap list into dto
    @GetMapping("/discounts")
    public List<Discount> getAllDiscounts(){
        return discountService.getAll();
    }

    @PostMapping("sign-up")
    public ResponseDto signUpClient(@RequestBody SignUpDto dto){
        return clientService.saveClient(dto);
    }


}
