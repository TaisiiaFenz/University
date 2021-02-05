package ua.taisiia.model.dao;

import ua.taisiia.model.entity.Client;
import ua.taisiia.model.entity.Reservation;

import java.util.List;

public interface ReservationDao extends Dao<Reservation>{
    List<Reservation> findByClient(Long clientId);
    List<Reservation> findByTour(Long tourId);
    List<Reservation> findBySale(Long saleId);

    Integer countAll();
}
