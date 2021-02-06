package ua.taisiia.model.dao.mapper;

import ua.taisiia.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TourMapper implements Mapper<Tour> {
    @Override
    public Tour resultSetToEntity(ResultSet resultSet) throws SQLException {
        Tour tour = new Tour();
        tour.setId(resultSet.getLong("id"));
        tour.setTourType(TourType.valueOf(resultSet.getString("tour_type")));
        tour.setTransportType(TransportType.valueOf(resultSet.getString("transport_type")));
        tour.setCountry(resultSet.getString("country"));
        tour.setCostPerTour(resultSet.getBigDecimal("cost_per_tour"));
        tour.setDurationInDays(resultSet.getInt("duration_in_days"));
        tour.setLastMinuteTour(resultSet.getBoolean("is_last_minute_tour"));
        return tour;
    }



    @Override
    public Tour makeUnique(Map<Long, Tour> cache, Tour entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
