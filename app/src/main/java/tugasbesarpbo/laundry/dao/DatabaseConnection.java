
package tugasbesarpbo.laundry.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/laundry_db";

    private static final String USER =
            System.getenv("LAUNDRY_DB_USER");

    private static final String PASSWORD =
            System.getenv("LAUNDRY_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        if (USER == null || PASSWORD == null) {
            throw new SQLException(
                    "Konfigurasi LAUNDRY_DB_USER dan "
                    + "LAUNDRY_DB_PASSWORD belum diatur."
            );
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
