package ua.taisiia.model.dao.mapper;

import ua.taisiia.model.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ReservationMapper implements Mapper<Reservation>{

    @Override
    public Reservation resultSetToEntity(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getLong("id"));
        reservation.setClientId(resultSet.getLong("client_id"));
        reservation.setTourId(resultSet.getLong("tour_id"));
        reservation.setDiscountId(resultSet.getLong("discount_id"));
        reservation.setDepartureDate(resultSet.getDate("departure_date").toLocalDate());
        return reservation;
    }



    @Override
    public Reservation makeUnique(Map<Long, Reservation> cache, Reservation entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
