
package tugasbesarpbo.laundry.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PelangganView extends JFrame {

    private JTextField idField;
    private JTextField namaField;
    private JTextField teleponField;
    private JTable tabelPelanggan;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    private JButton tambahButton;
    private JButton ubahButton;
    private JButton hapusButton;
    private JButton muatButton;
    private JButton bersihkanButton;

    public PelangganView() {
        setTitle("Kelola Pelanggan - Laundry");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        idField = new JTextField();
        idField.setEditable(false);

        namaField = new JTextField();
        teleponField = new JTextField();

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        formPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("ID Pelanggan:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Nama:"));
        formPanel.add(namaField);
        formPanel.add(new JLabel("Nomor Telepon:"));
        formPanel.add(teleponField);

        tableModel = new DefaultTableModel(
                new String[]{"ID", "Nama", "Nomor Telepon"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelPelanggan = new JTable(tableModel);
        tabelPelanggan.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        tabelPelanggan.getSelectionModel()
                .addListSelectionListener(e -> {
                    if (!e.getValueIsAdjusting()) {
                        int row = tabelPelanggan.getSelectedRow();

                        if (row >= 0) {
                            idField.setText(
                                    tableModel.getValueAt(row, 0).toString());
                            namaField.setText(
                                    tableModel.getValueAt(row, 1).toString());

                            Object telepon =
                                    tableModel.getValueAt(row, 2);

                            teleponField.setText(
                                    telepon == null ? "" : telepon.toString());
                        }
                    }
                });

        JPanel buttonPanel = new JPanel();

        tambahButton = new JButton("Tambah");
        ubahButton = new JButton("Ubah");
        hapusButton = new JButton("Hapus");
        muatButton = new JButton("Muat Ulang");
        bersihkanButton = new JButton("Bersihkan");

        buttonPanel.add(tambahButton);
        buttonPanel.add(ubahButton);
        buttonPanel.add(hapusButton);
        buttonPanel.add(muatButton);
        buttonPanel.add(bersihkanButton);

        statusLabel = new JLabel("Siap.");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(tabelPelanggan), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public String getId() {
        return idField.getText();
    }

    public String getNama() {
        return namaField.getText().trim();
    }

    public String getTelepon() {
        return teleponField.getText().trim();
    }

    public int getSelectedRow() {
        return tabelPelanggan.getSelectedRow();
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    public void setTambahAction(Runnable action) {
        tambahButton.addActionListener(e -> action.run());
    }

    public void setUbahAction(Runnable action) {
        ubahButton.addActionListener(e -> action.run());
    }

    public void setHapusAction(Runnable action) {
        hapusButton.addActionListener(e -> action.run());
    }

    public void setMuatAction(Runnable action) {
        muatButton.addActionListener(e -> action.run());
    }

    public void setBersihkanAction(Runnable action) {
        bersihkanButton.addActionListener(e -> action.run());
    }

    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        teleponField.setText("");
        tabelPelanggan.clearSelection();
    }

    public void setPelangganData(Object[][] data) {
        tableModel.setRowCount(0);

        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public void showWindow() {
        setVisible(true);
    }
}
