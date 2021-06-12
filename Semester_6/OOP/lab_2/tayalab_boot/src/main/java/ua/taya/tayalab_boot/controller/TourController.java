package ua.taya.tayalab_boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.taya.tayalab_boot.dto.ToursDto;
import ua.taya.tayalab_boot.service.TourService;

@RestController
@RequestMapping("tours")
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:8082")
    public ToursDto getTours(){
        System.out.println("get tours");
        return tourService.getAllTours();
    }
}
