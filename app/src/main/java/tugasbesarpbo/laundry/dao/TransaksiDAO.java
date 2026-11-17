
package tugasbesarpbo.laundry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    public List<Object[]> getPelanggan() throws SQLException {
        List<Object[]> daftar = new ArrayList<>();

        String sql = """
            SELECT id_pelanggan, nama
            FROM pelanggan
            ORDER BY nama
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                daftar.add(new Object[]{
                    result.getInt("id_pelanggan"),
                    result.getString("nama")
                });
            }
        }

        return daftar;
    }

    public List<Object[]> getLayanan() throws SQLException {
        List<Object[]> daftar = new ArrayList<>();

        String sql = """
            SELECT id_layanan, nama_layanan, harga_per_kg
            FROM layanan
            ORDER BY nama_layanan
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                daftar.add(new Object[]{
                    result.getInt("id_layanan"),
                    result.getString("nama_layanan"),
                    result.getDouble("harga_per_kg")
                });
            }
        }

        return daftar;
    }

    public void buatPesanan(
            int idPelanggan,
            int idLayanan,
            double berat,
            double totalHarga) throws SQLException {

        String sql = """
            INSERT INTO pesanan
                (id_pelanggan, id_layanan, berat, total_harga, status)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idPelanggan);
            statement.setInt(2, idLayanan);
            statement.setDouble(3, berat);
            statement.setDouble(4, totalHarga);
            statement.setString(5, "MENUNGGU");

            statement.executeUpdate();
        }
    }

    public List<Object[]> getPesanan() throws SQLException {
        List<Object[]> daftar = new ArrayList<>();

        String sql = """
            SELECT p.id_pesanan, pl.nama, l.nama_layanan,
                   p.berat, p.total_harga, p.status
            FROM pesanan p
            JOIN pelanggan pl ON p.id_pelanggan = pl.id_pelanggan
            JOIN layanan l ON p.id_layanan = l.id_layanan
            ORDER BY p.id_pesanan DESC
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                daftar.add(new Object[]{
                    result.getInt("id_pesanan"),
                    result.getString("nama"),
                    result.getString("nama_layanan"),
                    result.getDouble("berat"),
                    result.getBigDecimal("total_harga"),
                    result.getString("status")
                });
            }
        }

        return daftar;
    }

    
    public void updateStatus(int idPesanan, String status) throws SQLException {
        String sql = """
            UPDATE pesanan
            SET status = ?
            WHERE id_pesanan = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, idPesanan);

            int jumlahBaris = statement.executeUpdate();

            if (jumlahBaris == 0) {
                throw new SQLException("Pesanan tidak ditemukan.");
            }
        }
    }

}