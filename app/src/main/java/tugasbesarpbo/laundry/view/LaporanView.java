
package tugasbesarpbo.laundry.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class LaporanView extends JFrame {

    public LaporanView(
            int jumlahPesanan,
            BigDecimal totalPendapatan,
            List<Object[]> dataStatus) {

        setTitle("Laporan Sistem Manajemen Laundry");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Format mata uang Rupiah
        NumberFormat formatRupiah =
                NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        // Judul laporan
        JLabel judul = new JLabel(
                "LAPORAN SISTEM LAUNDRY",
                SwingConstants.CENTER
        );
        judul.setFont(new Font("Arial", Font.BOLD, 22));

        // Panel informasi laporan
        JPanel infoPanel = new JPanel(new BorderLayout());

        JLabel jumlahLabel = new JLabel(
                "Jumlah Seluruh Pesanan: " + jumlahPesanan,
                SwingConstants.CENTER
        );
        jumlahLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel pendapatanLabel = new JLabel(
                "Total Pendapatan: " +
                formatRupiah.format(totalPendapatan),
                SwingConstants.CENTER
        );
        pendapatanLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel ringkasanPanel = new JPanel(
                new java.awt.GridLayout(2, 1, 5, 5)
        );
        ringkasanPanel.add(jumlahLabel);
        ringkasanPanel.add(pendapatanLabel);

        infoPanel.add(judul, BorderLayout.NORTH);
        infoPanel.add(ringkasanPanel, BorderLayout.CENTER);

        // Tabel laporan berdasarkan status
        String[] kolom = {
            "Status Pesanan",
            "Jumlah Pesanan"
        };

        DefaultTableModel model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Object[] data : dataStatus) {
            model.addRow(data);
        }

        JTable tabelLaporan = new JTable(model);
        tabelLaporan.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabelLaporan);

        // Tombol tutup
        JButton tutupButton = new JButton("Tutup");
        tutupButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(tutupButton);

        // Mengatur layout utama
        setLayout(new BorderLayout(10, 15));

        add(infoPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
