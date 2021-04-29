package dao;

import models.Catalog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogDaoImpl implements DAO<Catalog>{
    Connection connection;

    public CatalogDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Catalog> findAll() {
        List<Catalog> catalogList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT  * FROM catalog");
            while (resultSet.next()) {
                Catalog catalog = new Catalog();
                catalog.setId(resultSet.getInt("id"));
                catalog.setName(resultSet.getString("name"));
                catalog.setCatalog(resultSet.getString("catalog"));
                catalogList.add(catalog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogList;
    }

    @Override
    public Catalog save(Catalog item) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("INSERT INTO catalog(name,catalog) VALUES(?,?)");
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getCatalog());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM catalog WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void close() throws Exception { }
}
