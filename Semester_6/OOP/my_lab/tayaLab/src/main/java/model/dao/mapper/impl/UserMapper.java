package model.dao.mapper.impl;

import model.dao.mapper.Mapper;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper<T> implements Mapper<User> {
    @Override
    public User resultSetToEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setPassword(resultSet.getString("password"));
        user.setLogin(resultSet.getString("username"));
        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
