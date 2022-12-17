package xyz.michelepip.fp.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPTesting {

    private HikariCPTesting(){} // no initializations

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    // test details

    static {
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/testdb");
        config.setUsername("hikaricpt");
        config.setPassword("hiikaritestuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "200");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    public static void main(String[] args) {
        try {
            System.out.println(Db.retData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
