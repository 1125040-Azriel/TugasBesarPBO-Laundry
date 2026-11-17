
package tugasbesarpbo.laundry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tugasbesarpbo.laundry.model.Role;

public class UserDAO {

    public Role authenticate(String username, String password)
            throws SQLException {

        String sql = """
                SELECT role
                FROM pengguna
                WHERE username = ?
                  AND password_hash = crypt(?, password_hash)
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Role.valueOf(result.getString("role"));
                }
            }
        }

        return null;
    }
}
