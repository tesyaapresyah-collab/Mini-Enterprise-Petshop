package view;

import entity.Pelanggan;
import repository.PelangganRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PelangganView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private PelangganRepository repo = new PelangganRepository();
    private JTextField txtNama, txtTelp, txtAlamat;

    public PelangganView() {
        setTitle("Manajemen Data Pelanggan");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel Input
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Pelanggan"));
        
        txtNama = new JTextField();
        txtTelp = new JTextField();
        txtAlamat = new JTextField();
        
        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        btnHapus.setBackground(Color.RED);
        btnHapus.setForeground(Color.WHITE);

        inputPanel.add(new JLabel("Nama:")); inputPanel.add(txtNama);
        inputPanel.add(new JLabel("No Telp:")); inputPanel.add(txtTelp);
        inputPanel.add(new JLabel("Alamat:")); inputPanel.add(txtAlamat);
        inputPanel.add(btnTambah); inputPanel.add(btnUpdate);

        // Tabel
        String[] columns = {"ID", "Nama", "No Telp", "Alamat"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        // --- LOGIKA MOUSE KLIK (Untuk Update) ---
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                txtNama.setText(tableModel.getValueAt(row, 1).toString());
                txtTelp.setText(tableModel.getValueAt(row, 2).toString());
                txtAlamat.setText(tableModel.getValueAt(row, 3).toString());
            }
        });

        // --- LOGIKA TAMBAH ---
        btnTambah.addActionListener(e -> {
            repo.add(new Pelanggan(0, txtNama.getText(), txtTelp.getText(), txtAlamat.getText()));
            loadData();
            clearFields();
        });

        // --- LOGIKA UPDATE ---
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "Pilih data di tabel!"); return; }
            int id = (int) tableModel.getValueAt(row, 0);
            repo.update(new Pelanggan(id, txtNama.getText(), txtTelp.getText(), txtAlamat.getText()));
            loadData();
            clearFields();
        });

        // --- LOGIKA HAPUS ---
        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "Pilih data di tabel!"); return; }
            int id = (int) tableModel.getValueAt(row, 0);
            repo.delete(id);
            loadData();
            clearFields();
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnHapus, BorderLayout.SOUTH); // Tombol Hapus ditaruh di bawah agar beda

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Pelanggan> list = repo.getAll();
        for (Pelanggan p : list) {
            tableModel.addRow(new Object[]{
                p.getIdPelanggan(), p.getNamaPelanggan(), p.getNoTelp(), p.getAlamat()
            });
        }
    }
    
    private void clearFields() {
        txtNama.setText(""); txtTelp.setText(""); txtAlamat.setText("");
    }
}