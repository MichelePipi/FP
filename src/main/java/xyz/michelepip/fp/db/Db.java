package xyz.michelepip.fp.db;

import org.jetbrains.annotations.TestOnly;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Db {


    private Db(){}


    @TestOnly
    public static List<String> retData() throws SQLException {
        List<String> out = new ArrayList<>();
        try (Connection conn = HikariCPTesting.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * from a");
             ResultSet results = ps.executeQuery()) {
            while (results.next()) {
                out.add(results.getString("aste"));
                out.add(results.getString("a"));
                out.add(results.getString("r"));
                out.add(results.getString("h"));
            }
        }
        return out;
    }
}
