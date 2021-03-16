package model.service;

import com.google.gson.Gson;
import model.dao.DaoFactory;
import model.dao.TourDao;
import model.dto.ResponseDto;
import model.entity.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Tour> getAllTours() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public String updateHotTour(Long tourId){
        Gson gson = new Gson();
        try(TourDao tourDao = daoFactory.createTourDao()){
            Tour tour = tourDao.findById(tourId);
            if(tour.getLastMinuteTour()) tour.setLastMinuteTour(false);
            else tour.setLastMinuteTour(true);
            tourDao.update(tour);
            return gson.toJson(new ResponseDto(true, "Status of tour is changed"));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseDto(false, "Status of tour is not changed"));
        }
    }

}
