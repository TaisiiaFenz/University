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
@RequestMapping("agent")
@RequiredArgsConstructor
public class AgentController {
    private final TourService tourService;
    private final ReservationService reservationService;
    private final DiscountService discountService;
    private final ClientService clientService;

    @PutMapping("/update-hot-tour/{id}")
    @CrossOrigin(origins = "http://localhost:8082")
    public ResponseDto updateHotTour(@PathVariable("id") Long tourId) {
        System.out.println("Update hot tour");
        return tourService.updateHot(tourId);
    }

    @PutMapping("/cancel-reservation/{id}")
    @CrossOrigin(origins = "http://localhost:8082")
    public ResponseDto cancelReservation(@PathVariable("id") Long reservationId) {
        return reservationService.cancelReservation(reservationId);
    }

    @PostMapping("/accept-reservation")
    @CrossOrigin(origins = "http://localhost:8082")
    public ResponseDto acceptReservation(@RequestBody BillingDto billingDto){
        return reservationService.acceptReservation(billingDto);
    }

    @GetMapping("/reserved-tours")
    @CrossOrigin(origins = "http://localhost:8082")
    public ReservedToursDto getAllReservations(){
        return reservationService.getAllReservations();
    }

    //TODO: it is better to wrap list into dto
    @GetMapping("/discounts")
    @CrossOrigin(origins = "http://localhost:8082")
    public List<Discount> getAllDiscounts(){
        System.out.println("discounts");
        return discountService.getAll();
    }

    @PostMapping("/sign-up")
    @CrossOrigin(origins = "http://localhost:8082")
    public ResponseDto signUpClient(@RequestBody SignUpDto dto){
        System.out.println("sign up");
        return clientService.saveClient(dto);
    }


}
