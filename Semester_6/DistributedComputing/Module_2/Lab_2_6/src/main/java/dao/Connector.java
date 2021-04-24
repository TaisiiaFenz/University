package dao;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.ConnectionPool;

import javax.sql.DataSource;

public class Connector {

    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
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
