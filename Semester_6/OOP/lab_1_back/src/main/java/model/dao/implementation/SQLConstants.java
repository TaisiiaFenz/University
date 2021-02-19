package model.dao.implementation;

public interface SQLConstants {
    String SQL_CLIENT_INSERT = "INSERT into client (" +
            "first_name, " +
            "middle_name, " +
            "last_name, " +
            "passport, " +
            "birthday, " +
            "regular_clients," +
            "user_id)" +
            "VALUES (?,?,?,?,?,?,?)";

    String SQL_USER_INSERT = "INSERT into users (" +
            "username, " +
            "password) " +
            "VALUES (?,?)";

    String SQL_USER_ROLE_INSERT = "INSERT into user_role (" +
            "user_id, " +
            "authorities) " +
            "VALUES(?,?)";

    String SQL_USER_FIND_BY_USERNAME = "SELECT * FROM users WHERE username=?";

    String SQL_USER_ROLE_FIND_BY_USER = "SELECT * FROM user_role WHERE user_id=?";

    String SQL_TOURS_FIND_ALL = "SELECT * FROM tour";

    String SQL_TOUR_FIND_BY_ID = "SELECT * FROM tour WHERE id = ?";

    String SQL_TOUR_UPDATE = "UPDATE users SET (" +
            "name, " +
            "country, " +
            "type," +
            "price," +
            "isHot) " +
            "WHERE id=?";
}
