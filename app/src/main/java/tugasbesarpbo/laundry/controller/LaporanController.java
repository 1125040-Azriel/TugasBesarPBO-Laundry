
package tugasbesarpbo.laundry.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import tugasbesarpbo.laundry.dao.LaporanDAO;
import tugasbesarpbo.laundry.view.LaporanView;

public class LaporanController {

    private LaporanDAO laporanDAO;

    public LaporanController() {
        laporanDAO = new LaporanDAO();
    }

    public void start() {
        try {
            // Mengambil jumlah seluruh pesanan
            int jumlahPesanan = laporanDAO.getJumlahPesanan();

            // Mengambil total pendapatan
            BigDecimal totalPendapatan =
                    laporanDAO.getTotalPendapatan();

            // Mengambil jumlah pesanan berdasarkan status
            List<Object[]> dataStatus =
                    laporanDAO.getJumlahPesananPerStatus();

            // Membuka tampilan laporan
            LaporanView view = new LaporanView(
                    jumlahPesanan,
                    totalPendapatan,
                    dataStatus
            );

            view.setVisible(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Gagal mengambil data laporan: " + e.getMessage(),
                    "Kesalahan Database",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
