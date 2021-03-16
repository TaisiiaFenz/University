package model.dao.implementation;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.mapper.impl.ClientMapper;
import model.entity.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ClientDaoImpl implements ClientDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    //private static final Logger LOGGER = LogManager.getLogger(ClientDaoImpl.class);

    public ClientDaoImpl(Connection connection) {
        this.connection = connection;
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_CLIENT_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findClientsByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public Optional<Client> findByUserId(Long userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_CLIENT_FIND_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            List<Client> clientsByPreparedStatement = findClientsByPreparedStatement(preparedStatement);
            if (clientsByPreparedStatement.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(clientsByPreparedStatement.get(0));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    private List<Client> findClientsByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        Map<Long, Client> clients = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        ClientMapper clientMapper = new ClientMapper();
        UserDao userDao = daoFactory.createUserDao();
        while (resultSet.next()) {
            System.out.println(resultSet);
            Client client = clientMapper.resultSetToEntity(resultSet);
            User user = userDao.findById(resultSet.getLong("user_id"));
            client.setUser(user);
            clientMapper.makeUnique(clients, client);
        }
        userDao.close();
        return new ArrayList<>(clients.values());
    }
}
