package model.dao.implementation;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.dao.ReservationDao;
import model.dao.UserRoleDao;
import model.dao.mapper.impl.ReservationMapper;
import model.dao.mapper.impl.UserMapper;
import model.entity.Reservation;
import model.entity.Role;
import model.entity.User;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ReservationDaoImpl implements ReservationDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public ReservationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Reservation entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_RESERVATION_INSERT, Statement.RETURN_GENERATED_KEYS);
        fillPreparedStatement(entity, preparedStatement);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            entity.setId(generatedKeys.getLong(1));
        }
    }

    private void fillPreparedStatement(Reservation entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getClientId());
        preparedStatement.setLong(2, entity.getTourId());
        preparedStatement.setDate(3, Date.valueOf(entity.getStartDate()));
        preparedStatement.setDate(4, Date.valueOf(entity.getEndDate()));
        preparedStatement.setString(5, entity.getStatus().toString());
        preparedStatement.setDate(6, Date.valueOf(entity.getReservationDate()));
    }

    @Override
    public void update(Reservation entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_RESERVATION_UPDATE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Reservation findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_RESERVATION_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findReservationsByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_RESERVATION_FIND_ALL)) {
            return findReservationsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void close() throws Exception {

    }

    private List<Reservation> findReservationsByPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        Map<Long, Reservation> reservations = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        ReservationMapper reservationMapper = new ReservationMapper();

        while (resultSet.next()) {
            Reservation reservation = reservationMapper.resultSetToEntity(resultSet);
            reservationMapper.makeUnique(reservations, reservation);
        }
        return new ArrayList<>(reservations.values());
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_RESERVATION_FIND_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            return findReservationsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}

