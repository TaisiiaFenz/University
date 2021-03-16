package model.dao.mapper.impl;

import model.dao.mapper.Mapper;
import model.entity.Reservation;
import model.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ReservationMapper implements Mapper<Reservation> {
    @Override
    public Reservation resultSetToEntity(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getLong("id"));
        reservation.setTourId(resultSet.getLong("tour_id"));
        reservation.setClientId(resultSet.getLong("client_id"));
        reservation.setStatus(Status.valueOf(resultSet.getString("status")));
        reservation.setStartDate(resultSet.getDate("start_date").toLocalDate());
        reservation.setEndDate(resultSet.getDate("end_date").toLocalDate());
        reservation.setReservationDate(resultSet.getDate("reservation_date").toLocalDate());
        return reservation;
    }

    @Override
    public Reservation makeUnique(Map<Long, Reservation> cache, Reservation entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
