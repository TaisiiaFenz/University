package dao;

import models.City;

import java.sql.Connection;
import java.util.List;

public class CityDaoImpl implements DAO<City> {

    Connection connection;

    public CityDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<City> findAll() {
        return null;
    }

    @Override
    public City save(City item) {
        return null;
    }

    @Override
    public City findById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}
