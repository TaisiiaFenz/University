package dao;

import models.Catalog;
import models.File;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDaoImpl implements DAO<File>{
    Connection connection;

    public FileDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<File> findAll() {
        List<File> fileList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT  * FROM file");
            while (resultSet.next()) {
                File file = new File();
                file.setId(resultSet.getInt("file_id"));
                file.setName(resultSet.getString("name"));
                file.setCatalog(resultSet.getString("catalog"));
                file.setSize(resultSet.getInt("size"));
                fileList.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    @Override
    public File save(File item) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("INSERT INTO file(name,catalog,size) VALUES(?,?,?)");
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getCatalog());
            preparedStatement.setInt(3, item.getSize());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM file WHERE file_id=?");
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
