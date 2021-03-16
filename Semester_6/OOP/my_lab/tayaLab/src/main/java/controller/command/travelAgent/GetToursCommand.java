package controller.command.travelAgent;

import com.google.gson.Gson;
import controller.command.Command;
import model.dto.ToursDto;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class GetToursCommand implements Command {
    TourService tourService;

    public GetToursCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Gson gson = new Gson();
        return gson.toJson(new ToursDto(tourService.getAllTours()));
    }
}
