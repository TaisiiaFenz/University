package model.dao.implementation;

import model.dao.DaoFactory;
import model.dao.TourDao;
import model.dao.mapper.impl.TourMapper;
import model.entity.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TourDaoImpl implements TourDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public TourDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Tour entity) throws SQLException {

    }

    @Override
    public void update(Tour entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_TOUR_UPDATE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    private void fillPreparedStatement(Tour entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getCountry());
        preparedStatement.setString(3, entity.getTourType().toString());
        preparedStatement.setBigDecimal(4, entity.getPrice());
        preparedStatement.setBoolean(5, entity.getLastMinuteTour());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Tour findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_TOUR_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findToursByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_TOURS_FIND_ALL)) {
            return findToursByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("SQL State: " + e.getSQLState() + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<Tour> findToursByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        Map<Long, Tour> tours = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        TourMapper tourMapper = new TourMapper();
        while (resultSet.next()) {
            Tour tour = tourMapper.resultSetToEntity(resultSet);
            tour = tourMapper.makeUnique(tours, tour);
        }
        return new ArrayList<>(tours.values());
    }

    @Override
    public void close() throws Exception {

    }
}
