package model.dao.implementation;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.UserRoleDao;
import model.entity.Role;
import model.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public UserRoleDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(UserRole entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_USER_ROLE_INSERT);
        fillPreparedStatement(entity, preparedStatement);
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(UserRole entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserRole findById(Long id) {
        return null;
    }

    @Override
    public List<UserRole> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }

    private void fillPreparedStatement(UserRole entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getUser().getId());
        preparedStatement.setString(2, entity.getRole().toString());
    }

    @Override
    public List<Role> findRolesByUser(Long userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_USER_ROLE_FIND_BY_USER)) {
            preparedStatement.setLong(1, userId);
            return findRolesByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Role> findRolesByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        List<Role> roles = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDao userDao = daoFactory.createUserDao();

        while (resultSet.next()) {
            Role role = Role.valueOf(resultSet.getString("authorities"));
            roles.add(role);
        }
        userDao.close();
        return roles;
    }
}
