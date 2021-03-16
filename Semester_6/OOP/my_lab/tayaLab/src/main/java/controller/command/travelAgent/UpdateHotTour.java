package controller.command.travelAgent;

import controller.command.Command;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class UpdateHotTour implements Command {
    TourService tourService;

    public UpdateHotTour(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        return tourService.updateHotTour(id);
    }
}
