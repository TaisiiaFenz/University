package dao;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.ConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static DataSource dataSource;

    public static Connection getConnection() {
        Connection conn = null;
        try {
        Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/world-map",
                    "postgres", "02vapiro");
            System.out.println("Part 1");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

//        String url = "jdbc:postgresql://localhost:5432/world-map";
//        Properties props = new Properties();
//        props.setProperty("user","fred");
//        props.setProperty("password","secret");
//        props.setProperty("ssl","true");
//        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (Connector.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName("org.postgresql.Driver");
                    ds.setUrl("jdbc:postgresql://localhost:5432/world-map");
                    ds.setUsername("postgres");
                    ds.setPassword("02vapiro");
                    ds.setMaxTotal(200);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
