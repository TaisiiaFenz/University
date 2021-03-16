package model.dao.implementation;

import model.dao.DaoFactory;
import model.dao.DiscountDao;
import model.dao.mapper.impl.DiscountMapper;
import model.dao.mapper.impl.ReservationMapper;
import model.entity.Discount;
import model.entity.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountDaoImpl implements DiscountDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public DiscountDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Discount entity) throws SQLException {

    }

    @Override
    public void update(Discount entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Discount findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_DISCOUNT_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findDiscountsByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Discount> findDiscountsByPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        Map<Long, Discount> discounts = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        DiscountMapper discountMapper = new DiscountMapper();

        while (resultSet.next()) {
            Discount discount = discountMapper.resultSetToEntity(resultSet);
            discountMapper.makeUnique(discounts, discount);
        }
        return new ArrayList<>(discounts.values());
    }

    @Override
    public List<Discount> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_DISCOUNT_FIND_ALL)) {
            return findDiscountsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("SQL State: " + e.getSQLState() + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
