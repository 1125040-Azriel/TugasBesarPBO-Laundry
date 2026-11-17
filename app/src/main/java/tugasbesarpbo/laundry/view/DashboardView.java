
package tugasbesarpbo.laundry.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tugasbesarpbo.laundry.controller.LayananController;
import tugasbesarpbo.laundry.controller.LihatPesananController;
import tugasbesarpbo.laundry.controller.LaporanController;
import tugasbesarpbo.laundry.controller.LoginController;
import tugasbesarpbo.laundry.controller.PelangganController;
import tugasbesarpbo.laundry.controller.PembayaranController;
import tugasbesarpbo.laundry.controller.StatusLaundryController;
import tugasbesarpbo.laundry.controller.TransaksiController;
import tugasbesarpbo.laundry.model.User;

public class DashboardView extends JFrame {

    public DashboardView(User user) {
        setTitle("Sistem Manajemen Laundry - Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel(
                "Selamat datang, " + user.getNamaRole(),
                SwingConstants.CENTER
        );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel menuPanel = new JPanel();

        switch (user.getRole()) {

            case ADMIN:
                JButton layananAdmin = new JButton("Kelola Layanan");
                layananAdmin.addActionListener(e -> {
                    LayananController controller = new LayananController();
                    controller.start();
                });
                menuPanel.add(layananAdmin);

                JButton pelangganAdmin = new JButton("Kelola Pelanggan");
                pelangganAdmin.addActionListener(e -> {
                    PelangganController controller = new PelangganController();
                    controller.start();
                });
                menuPanel.add(pelangganAdmin);

                JButton laporanAdmin = new JButton("Lihat Laporan");
                laporanAdmin.addActionListener(e -> {
                    LaporanController controller = new LaporanController();
                    controller.start();
                });
                menuPanel.add(laporanAdmin);
                break;

            case KASIR:
                JButton transaksiButton = new JButton("Buat Transaksi");
                transaksiButton.addActionListener(e -> {
                    TransaksiController controller = new TransaksiController();
                    controller.start();
                });
                menuPanel.add(transaksiButton);

                JButton pembayaranButton = new JButton("Proses Pembayaran");
                pembayaranButton.addActionListener(e -> {
                    PembayaranController controller = new PembayaranController();
                    controller.start();
                });
                menuPanel.add(pembayaranButton);

                JButton lihatPesananKasir = new JButton("Lihat Pesanan");
                lihatPesananKasir.addActionListener(e -> {
                    LihatPesananController controller =
                            new LihatPesananController();
                    controller.start();
                });
                menuPanel.add(lihatPesananKasir);

                JButton pelangganKasir = new JButton("Kelola Pelanggan");
                pelangganKasir.addActionListener(e -> {
                    PelangganController controller = new PelangganController();
                    controller.start();
                });
                menuPanel.add(pelangganKasir);
                break;

            case PETUGAS:
                JButton lihatPesananPetugas = new JButton("Lihat Pesanan");
                lihatPesananPetugas.addActionListener(e -> {
                    LihatPesananController controller =
                            new LihatPesananController();
                    controller.start();
                });
                menuPanel.add(lihatPesananPetugas);

                JButton statusLaundryButton =
                        new JButton("Update Status Laundry");
                statusLaundryButton.addActionListener(e -> {
                    StatusLaundryController controller =
                            new StatusLaundryController();
                    controller.start();
                });
                menuPanel.add(statusLaundryButton);
                break;
        }

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            LoginController controller = new LoginController();
            controller.start();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(logoutButton);

        setLayout(new BorderLayout(10, 10));
        add(welcomeLabel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
