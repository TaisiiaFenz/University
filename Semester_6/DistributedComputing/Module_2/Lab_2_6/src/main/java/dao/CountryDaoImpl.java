package dao;

import models.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements DAO<Country> {

    Connection connection;

    public CountryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Country> findAll() {
        List<Country> countryList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT  * FROM country");
            while (resultSet.next()) {
                Country country = new Country();
                country.setCountryId(resultSet.getInt("country_id"));
                country.setCountryName(resultSet.getString("country_name"));
                country.setCapital(resultSet.getString("capital"));
                countryList.add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryList;
    }

    @Override
    public Country save(Country country) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("INSERT  INTO country(country_name,capital) VALUES(?,?)");
            preparedStatement.setString(1, country.getCountryName());
            preparedStatement.setString(2, country.getCapital());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    public boolean update(Country country) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("UPDATE country SET country_name= ?, capital= ? WHERE id=?");
            preparedStatement.setString(1, country.getCountryName());
            preparedStatement.setString(2, country.getCapital());
            preparedStatement.setInt(3, country.getCountryId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Country findById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM country WHERE id=?");
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
