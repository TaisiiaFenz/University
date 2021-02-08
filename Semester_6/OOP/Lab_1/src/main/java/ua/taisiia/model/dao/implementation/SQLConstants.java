package ua.taisiia.model.dao.implementation;

public interface SQLConstants {
    String SQL_CLIENT_INSERT = "INSERT into client (" +
            "first_name, " +
            "middle_name, " +
            "last_name, " +
            "passport, " +
            "birthday, " +
            "regular_clients)" +
            "VALUES (?,?,?,?,?,?)";
}
