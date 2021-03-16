package controller.command.travelAgent;

import com.google.gson.Gson;
import controller.ReadData;
import controller.command.Command;
import model.dto.BillingDto;
import model.service.ReservationService;

import javax.servlet.http.HttpServletRequest;

public class AcceptReservationCommand implements Command {
    ReservationService reservationService;

    public AcceptReservationCommand(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        StringBuilder jb = ReadData.readData(request);
        Gson gson = new Gson();
        BillingDto billingDto = gson.fromJson(jb.toString(), BillingDto.class);
        return reservationService.acceptReservation(billingDto);
    }
}
