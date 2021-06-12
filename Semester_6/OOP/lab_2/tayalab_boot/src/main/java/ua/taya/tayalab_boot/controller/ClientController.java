package ua.taya.tayalab_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.taya.tayalab_boot.dto.ReservationDto;
import ua.taya.tayalab_boot.dto.ReservedToursDto;
import ua.taya.tayalab_boot.dto.ResponseDto;
import ua.taya.tayalab_boot.service.ReservationService;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("my-reservations")
    @CrossOrigin(origins = "http://localhost:8082")
    public ReservedToursDto getUserReservations(Authentication authentication){
        System.out.println("Get user reserv");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return reservationService.getByUserName(userDetails.getUsername());
    }

    @PostMapping("reserve-tour")
    @CrossOrigin(origins = "http://localhost:8082")
    public ResponseDto reserveTour(@RequestBody ReservationDto reservationDto){
        System.out.println("Reserve tour");
        return reservationService.saveReservation(reservationDto);
    }
}
