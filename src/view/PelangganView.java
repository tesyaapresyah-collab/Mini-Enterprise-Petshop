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
        
        setTitle("Data Pelanggan");
        setSize(600, 300);
        setLocationRelativeTo(null);
        
        // Setup Tabel
        String[] columns = {"ID", "Nama", "No Telp", "Alamat"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Muat data dari database
        loadData();
    }

    private void loadData() {
        List<Pelanggan> list = repository.getAll();
        for (Pelanggan p : list) {
            Object[] row = {p.getIdPelanggan(), p.getNamaPelanggan(), p.getNoTelp(), p.getAlamat()};
            tableModel.addRow(row);
        }
    }
}