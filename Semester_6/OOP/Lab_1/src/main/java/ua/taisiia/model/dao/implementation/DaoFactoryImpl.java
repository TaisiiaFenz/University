package ua.taisiia.model.dao.implementation;

import ua.taisiia.model.dao.DaoFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {
    private DataSource dataSource = ConnectionPool.getDataSource();

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClientDaoImpl createClientDao() {
        return new ClientDaoImpl(getConnection());
    }

    @Override
    public DiscountDaoImpl createDiscountDao() {
        return new DiscountDaoImpl(getConnection());
    }

    @Override
    public ReservationDaoImpl createReservationDao() {
        return new ReservationDaoImpl(getConnection());
    }

    @Override
    public TourDaoImpl createTourDao() {
        return new TourDaoImpl(getConnection());
    }


}
