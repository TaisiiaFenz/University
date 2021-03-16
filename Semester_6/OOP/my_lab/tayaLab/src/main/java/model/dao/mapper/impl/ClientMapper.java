package model.dao.mapper.impl;

import model.dao.mapper.Mapper;
import model.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ClientMapper implements Mapper<Client> {
    @Override
    public Client resultSetToEntity(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("id"));
        client.setFirstName(resultSet.getString("first_name"));
        client.setMiddleName(resultSet.getString("middle_name"));
        client.setLastName(resultSet.getString("last_name"));
        client.setBirthday(resultSet.getDate("birthday").toLocalDate());
        client.setPassport(resultSet.getString("passport"));
        client.setRegularClients(resultSet.getBoolean("regular_clients"));
        return client;
    }

    @Override
    public Client makeUnique(Map<Long, Client> cache, Client entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
