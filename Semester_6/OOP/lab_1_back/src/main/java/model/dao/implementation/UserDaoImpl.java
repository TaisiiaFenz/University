package model.dao.implementation;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.UserRoleDao;
import model.dao.mapper.impl.UserMapper;
import model.entity.Role;
import model.entity.User;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS);
        fillPreparedStatement(entity, preparedStatement);
        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        if (generatedKeys.next()) {
            entity.setId(generatedKeys.getLong(1));
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }

    private void fillPreparedStatement(User entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_USER_FIND_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            List<User> usersByPreparedStatement = findUsersByPreparedStatement(preparedStatement);
            if (usersByPreparedStatement.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(usersByPreparedStatement.get(0));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private List<User> findUsersByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
            Map<Long, User> users = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        UserMapper userMapper = new UserMapper();
        UserRoleDao userRoleDao = daoFactory.createUserRoleDao();

        while (resultSet.next()) {
            User user = userMapper.resultSetToEntity(resultSet);
            List<Role> userRoles = userRoleDao.findRolesByUser(user.getId());
            user.setAuthorities(new HashSet<>(userRoles));
            userMapper.makeUnique(users, user);
        }
        userRoleDao.close();
        return new ArrayList<>(users.values());
    }
}
