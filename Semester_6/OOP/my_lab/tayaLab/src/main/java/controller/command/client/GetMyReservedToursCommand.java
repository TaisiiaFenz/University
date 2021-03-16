package controller.command.client;

import com.google.gson.Gson;
import controller.command.Command;
import model.dto.ReservedToursDto;
import model.service.ReservationService;

import javax.servlet.http.HttpServletRequest;

public class GetMyReservedToursCommand implements Command {
    ReservationService reservationService;

    public GetMyReservedToursCommand(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        Gson gson = new Gson();
        Long userId = Long.valueOf(request.getParameter("id"));
        return gson.toJson(new ReservedToursDto(reservationService.findReservationsByUser(userId)));
    }
}
