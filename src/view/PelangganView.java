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
    private PelangganRepository repository;

    public PelangganView() {
        repository = new PelangganRepository();
        
        setTitle("Manajemen Pelanggan");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Setup Tabel
        String[] columns = {"ID", "Nama Pelanggan", "No Telp", "Alamat"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        // 2. ScrollPane agar tabel bisa di-scroll
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 3. Tombol Refresh
        JButton btnRefresh = new JButton("Refresh Data");
        btnRefresh.addActionListener(e -> loadData());
        add(btnRefresh, BorderLayout.SOUTH);

        // Load data pertama kali
        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0); // Kosongkan tabel sebelum diisi ulang
        List<Pelanggan> list = repository.getAll();
        
        for (Pelanggan p : list) {
            Object[] row = {
                p.getIdPelanggan(), 
                p.getNamaPelanggan(), 
                p.getNoTelp(), 
                p.getAlamat()
            };
            tableModel.addRow(row);
        }
    }
}