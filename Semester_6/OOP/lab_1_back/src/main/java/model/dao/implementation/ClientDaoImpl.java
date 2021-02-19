package model.dao.implementation;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entity.Client;
import model.entity.DiscountType;
import model.entity.TourType;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ClientDaoImpl implements ClientDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    //private static final Logger LOGGER = LogManager.getLogger(ClientDaoImpl.class);

    public ClientDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Client> findByClientName(String lastName, String firstName) {
        return Optional.empty();
    }

    @Override
    public List<Client> findAllRegularClients(Boolean regularClients) {
        return null;
    }

    @Override
    public List<Client> findClientsByDiscountType(DiscountType discountType) {
        return null;
    }

    @Override
    public List<Client> findClientsByTourType(TourType tourType) {
        return null;
    }

    @Override
    public void create(Client entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_CLIENT_INSERT, Statement.RETURN_GENERATED_KEYS);
        fillPreparedStatement(entity, preparedStatement);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            entity.setId(generatedKeys.getLong(1));
        }
    }

    @Override
    public void update(Client entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Client findById(Long id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }

    private void fillPreparedStatement(Client entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getMiddleName());
        preparedStatement.setString(3, entity.getLastName());
        preparedStatement.setString(4, entity.getPassport());
        preparedStatement.setDate(5, Date.valueOf(entity.getBirthday()));
        preparedStatement.setBoolean(6, entity.getRegularClients());
        preparedStatement.setLong(7, entity.getUser().getId());

    }
}
