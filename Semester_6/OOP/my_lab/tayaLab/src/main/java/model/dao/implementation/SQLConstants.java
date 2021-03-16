package model.dao.implementation;

import model.entity.Status;

import java.time.LocalDate;

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

    String SQL_TOUR_UPDATE = "UPDATE tour SET " +
            "is_hot=? " +
            "WHERE id=?";

    String SQL_RESERVATION_INSERT = "INSERT INTO reservation (" +
            "client_id, " +
            "tour_id, " +
            "start_date, " +
            "end_date, " +
            "status, " +
            "reservation_date) " +
            "VALUES(?,?,?,?,?,?)";

    String SQL_USER_FIND_BY_ID = "SELECT * FROM users WHERE id=?";

    String SQL_CLIENT_FIND_BY_USER_ID = "SELECT * FROM client WHERE user_id=?";

    String SQL_RESERVATION_FIND_ALL = "SELECT * FROM reservation";

    String SQL_CLIENT_FIND_BY_ID = "SELECT * FROM client WHERE id=?";

    String SQL_RESERVATION_UPDATE = "UPDATE reservation SET " +
            "client_id=?, " +
            "tour_id=?, " +
            "start_date=?, " +
            "end_date=?, " +
            "status=? " +
            "WHERE id=?";

    String SQL_RESERVATION_FIND_BY_ID = "SELECT * FROM reservation WHERE id=?";

    String SQL_BILLING_INSERT = "INSERT INTO billing (" +
            "reservation_id, " +
            "discount_id," +
            "sum)" +
            "VALUES(?,?,?)";

    String SQL_DISCOUNT_FIND_BY_ID = "SELECT * FROM discount WHERE id=?";

    String SQL_DISCOUNT_FIND_ALL = "SELECT * FROM discount";

    String SQL_RESERVATION_FIND_BY_USER_ID = "SELECT * FROM reservation LEFT JOIN client ON client.id=reservation.client_id where client.user_id=?";
}
