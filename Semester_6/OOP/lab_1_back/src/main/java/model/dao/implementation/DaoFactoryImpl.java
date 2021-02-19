package model.dao.implementation;

import model.dao.DaoFactory;
import model.dao.TourDao;

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
    public UserDaoImpl createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public UserRoleDaoImpl createUserRoleDao() {
        return new UserRoleDaoImpl(getConnection());
    }

    @Override
    public TourDao createTourDao() {
        return new TourDaoImpl(getConnection());
    }
}
