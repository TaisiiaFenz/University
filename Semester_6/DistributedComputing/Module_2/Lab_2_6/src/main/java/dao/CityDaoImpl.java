package dao;

import models.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl implements DAO<City> {

    Connection connection;

    public CityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<City> findAll() {
        List<City> cityList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT  * FROM city");
            while (resultSet.next()) {
                City city = new City();
                city.setCityId(resultSet.getInt("city_id"));
                city.setCityName(resultSet.getString("city_name"));
                city.setPopulationInThousands(resultSet.getInt("population_in_thousands"));
                city.setCountryId(resultSet.getInt("country_id"));
                cityList.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    @Override
    public City save(City item) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("INSERT INTO city(city_name,population_in_thousands,country_id) VALUES(?,?,?)");
            preparedStatement.setString(1, item.getCityName());
            preparedStatement.setInt(2, item.getPopulationInThousands());
            preparedStatement.setInt(3, item.getCountryId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public City findById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM city WHERE city_id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}
