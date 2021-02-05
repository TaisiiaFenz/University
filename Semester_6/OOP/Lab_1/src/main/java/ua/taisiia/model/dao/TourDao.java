package ua.taisiia.model.dao;

import ua.taisiia.model.entity.Tour;
import ua.taisiia.model.entity.TourType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TourDao extends Dao<Tour> {
    Optional<Tour> findAllRegularClients(Long tourId);
    List<Tour> findByTourType(TourType tourType);
    List<Tour> findByCountry(String country);
    List<Tour> findAllLastMinuteTour(Boolean lastMinuteTour);
    List<Tour> findAllToursInCostDiapazone(BigDecimal minCost, BigDecimal maxCost);
}
