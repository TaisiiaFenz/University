package controller.command.client;

import com.google.gson.*;
import controller.ReadData;
import controller.command.Command;
import controller.command.utility.CommandBCryptUtility;
import model.dto.ReservationDto;
import model.dto.SignUpDto;
import model.service.ReservationService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class ReserveTourCommand implements Command {
    ReservationService reservationService;

    public ReserveTourCommand(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        StringBuilder jb = ReadData.readData(request);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsString())).create();
        ReservationDto reservationDto = gson.fromJson(jb.toString(), ReservationDto.class);
        return reservationService.saveReservation(reservationDto);
    }
}
