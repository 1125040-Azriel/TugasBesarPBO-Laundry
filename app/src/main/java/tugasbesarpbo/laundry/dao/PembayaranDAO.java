
package tugasbesarpbo.laundry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PembayaranDAO {

    public List<Object[]> getPesananBelumDibayar() throws SQLException {
        List<Object[]> data = new ArrayList<>();

        String sql = """
            SELECT p.id_pesanan, pl.nama, l.nama_layanan,
                   p.berat, p.total_harga, p.status
            FROM pesanan p
            JOIN pelanggan pl ON p.id_pelanggan = pl.id_pelanggan
            JOIN layanan l ON p.id_layanan = l.id_layanan
            LEFT JOIN pembayaran b ON p.id_pesanan = b.id_pesanan
            WHERE b.id_pembayaran IS NULL
            ORDER BY p.id_pesanan DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                data.add(new Object[] {
                    rs.getInt("id_pesanan"),
                    rs.getString("nama"),
                    rs.getString("nama_layanan"),
                    rs.getBigDecimal("berat"),
                    rs.getBigDecimal("total_harga"),
                    rs.getString("status")
                });
            }
        }

        return data;
    }

    public void prosesPembayaran(
            int idPesanan,
            double jumlahBayar,
            String metodePembayaran) throws SQLException {

        String sql = """
            INSERT INTO pembayaran
                (id_pesanan, jumlah_bayar, metode_pembayaran)
            SELECT id_pesanan, total_harga, ?
            FROM pesanan
            WHERE id_pesanan = ?
              AND NOT EXISTS (
                  SELECT 1 FROM pembayaran
                  WHERE pembayaran.id_pesanan = pesanan.id_pesanan
              )
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, metodePembayaran);
            stmt.setInt(2, idPesanan);

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new SQLException(
                    "Pesanan tidak ditemukan atau sudah dibayar."
                );
            }
        }
    }
}