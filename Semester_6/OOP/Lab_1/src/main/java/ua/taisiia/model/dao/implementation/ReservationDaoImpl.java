package ua.taisiia.model.dao.implementation;

import ua.taisiia.model.dao.DaoFactory;
import ua.taisiia.model.dao.ReservationDao;
import ua.taisiia.model.entity.Reservation;

import java.sql.Connection;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public ReservationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Reservation> findByClient(Long clientId) {
        return null;
    }

    @Override
    public List<Reservation> findByTour(Long tourId) {
        return null;
    }

    @Override
    public List<Reservation> findBySale(Long saleId) {
        return null;
    }

    @Override
    public Integer countAll() {
        return null;
    }

    @Override
    public void create(Reservation entity)
            //throws DataExistsException
    {

    }

    @Override
    public void update(Reservation entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Reservation findById(Long id) {
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
