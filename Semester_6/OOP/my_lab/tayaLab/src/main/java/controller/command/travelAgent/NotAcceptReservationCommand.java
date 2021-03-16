package controller.command.travelAgent;

import controller.command.Command;
import model.service.ReservationService;

import javax.servlet.http.HttpServletRequest;

public class NotAcceptReservationCommand implements Command {
    private final ReservationService reservationService;

    public NotAcceptReservationCommand(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        reservationService.cancelReservation(Long.valueOf(request.getParameter("id")));
        return "";
    }
}
