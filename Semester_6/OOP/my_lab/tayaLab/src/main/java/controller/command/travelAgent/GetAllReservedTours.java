package controller.command.travelAgent;

import com.google.gson.Gson;
import controller.command.Command;
import model.dto.ReservedToursDto;
import model.service.ReservationService;

import javax.servlet.http.HttpServletRequest;

public class GetAllReservedTours implements Command {
    private ReservationService reservationService;

    public GetAllReservedTours(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        Gson gson = new Gson();
        return gson.toJson(new ReservedToursDto(reservationService.getAllReservations()));
    }
}
