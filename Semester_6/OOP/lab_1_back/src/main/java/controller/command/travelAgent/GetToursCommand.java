package controller.command.travelAgent;

import com.google.gson.Gson;
import controller.command.MultipleMethodCommand;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class GetToursCommand extends MultipleMethodCommand {
    TourService tourService;

    public GetToursCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        Gson gson = new Gson();
        return gson.toJson(tourService.getAllTours());
    }

    @Override
    protected String executePost(HttpServletRequest request) throws Exception {
        return null;
    }
}
