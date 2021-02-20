package controller.command.travelAgent;

import controller.command.MultipleMethodCommand;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class UpdateHotTour extends MultipleMethodCommand {
    TourService tourService;

    public UpdateHotTour(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        return tourService.updateHotTour(id);
    }

    @Override
    protected String executePost(HttpServletRequest request) throws Exception {
        return null;
    }
}
