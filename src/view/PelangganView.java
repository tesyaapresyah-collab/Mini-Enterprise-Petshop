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
    
    // Komponen Input
    private JTextField txtNama, txtTelp, txtAlamat;

    public PelangganView() {
        repository = new PelangganRepository();
        
        setTitle("Manajemen Data Pelanggan");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL INPUT (Bagian Atas) ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Tambah Pelanggan"));
        
        txtNama = new JTextField();
        txtTelp = new JTextField();
        txtAlamat = new JTextField();
        JButton btnTambah = new JButton("Tambah Data");

        inputPanel.add(new JLabel("Nama Pelanggan:")); inputPanel.add(txtNama);
        inputPanel.add(new JLabel("No. Telp:")); inputPanel.add(txtTelp);
        inputPanel.add(new JLabel("Alamat:")); inputPanel.add(txtAlamat);
        inputPanel.add(new JLabel()); // Spacer
        inputPanel.add(btnTambah);

        // --- PANEL TABEL (Bagian Tengah) ---
        String[] columns = {"ID", "Nama", "No Telp", "Alamat"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        // --- ACTION LISTENER (Logika Tambah) ---
        btnTambah.addActionListener(e -> {
            if (txtNama.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
                return;
            }
            
            Pelanggan p = new Pelanggan(0, txtNama.getText(), txtTelp.getText(), txtAlamat.getText());
            repository.add(p);
            
            // Bersihkan input dan refresh tabel
            txtNama.setText(""); txtTelp.setText(""); txtAlamat.setText("");
            loadData();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0); // Bersihkan tabel
        List<Pelanggan> list = repository.getAll();
        for (Pelanggan p : list) {
            tableModel.addRow(new Object[]{
                p.getIdPelanggan(), 
                p.getNamaPelanggan(), 
                p.getNoTelp(), 
                p.getAlamat()
            });
        }
    }
}