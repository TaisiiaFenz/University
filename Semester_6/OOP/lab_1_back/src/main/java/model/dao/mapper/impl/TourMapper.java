package model.dao.mapper.impl;

import model.dao.mapper.Mapper;
import model.entity.Tour;
import model.entity.TourType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TourMapper implements Mapper<Tour> {
    @Override
    public Tour resultSetToEntity(ResultSet resultSet) throws SQLException {
        Tour tour = new Tour();
        tour.setId(resultSet.getLong("id"));
        tour.setCountry(resultSet.getString("country"));
        tour.setLastMinuteTour(resultSet.getBoolean("isHot"));
        tour.setPrice(resultSet.getBigDecimal("price"));
        tour.setTourType(TourType.valueOf(resultSet.getString("type")));
        return tour;
    }

    @Override
    public Tour makeUnique(Map<Long, Tour> cache, Tour entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
