
package tugasbesarpbo.laundry.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            System.out.println("Koneksi database berhasil!");
            System.out.println(
                    "Database: "
                    + connection.getCatalog()
            );

        } catch (SQLException e) {
            System.out.println("Koneksi database gagal.");
            System.out.println("Pesan: " + e.getMessage());
        }
    }
}
