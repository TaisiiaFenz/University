package model.dao;

import model.entity.Reservation;

import java.util.List;

public interface ReservationDao extends Dao<Reservation> {
    List<Reservation> findByUserId(Long userId);
}
