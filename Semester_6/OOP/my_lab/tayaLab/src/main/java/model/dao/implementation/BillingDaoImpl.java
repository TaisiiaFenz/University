package model.dao.implementation;

import model.dao.BillingDao;
import model.dao.DaoFactory;
import model.entity.Billing;

import java.sql.*;
import java.util.List;

public class BillingDaoImpl implements BillingDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public BillingDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Billing entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_BILLING_INSERT, Statement.RETURN_GENERATED_KEYS);
        fillPreparedStatement(entity, preparedStatement);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            entity.setId(generatedKeys.getLong(1));
        }

    }

    private void fillPreparedStatement(Billing entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getReservation().getId());
        preparedStatement.setLong(2, entity.getDiscount().getId());
        preparedStatement.setBigDecimal(3, entity.getSum());
    }

    @Override
    public void update(Billing entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Billing findById(Long id) {
        return null;
    }

    @Override
    public List<Billing> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
