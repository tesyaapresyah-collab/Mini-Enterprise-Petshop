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
    
    private JTextField txtNama, txtTelp, txtAlamat;

    public PelangganView() {
        repository = new PelangganRepository();
        
        setTitle("Manajemen Data Pelanggan");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL INPUT ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Pelanggan"));
        
        txtNama = new JTextField();
        txtTelp = new JTextField();
        txtAlamat = new JTextField();
        JButton btnTambah = new JButton("Tambah Data");
        JButton btnHapus = new JButton("Hapus Data Terpilih");
        btnHapus.setBackground(Color.RED);
        btnHapus.setForeground(Color.WHITE);

        inputPanel.add(new JLabel("Nama:")); inputPanel.add(txtNama);
        inputPanel.add(new JLabel("Telp:")); inputPanel.add(txtTelp);
        inputPanel.add(new JLabel("Alamat:")); inputPanel.add(txtAlamat);
        inputPanel.add(btnTambah); inputPanel.add(btnHapus);

        // --- PANEL TABEL ---
        String[] columns = {"ID", "Nama", "No Telp", "Alamat"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        // --- LOGIKA TAMBAH ---
        btnTambah.addActionListener(e -> {
            if (txtNama.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama harus diisi!");
                return;
            }
            Pelanggan p = new Pelanggan(0, txtNama.getText(), txtTelp.getText(), txtAlamat.getText());
            repository.add(p);
            clearFields();
            loadData();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambah!");
        });

        // --- LOGIKA HAPUS ---
        btnHapus.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih baris di tabel untuk dihapus!");
                return;
            }
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                repository.delete(id);
                loadData();
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();
    }

    private void clearFields() {
        txtNama.setText(""); txtTelp.setText(""); txtAlamat.setText("");
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Pelanggan> list = repository.getAll();
        for (Pelanggan p : list) {
            tableModel.addRow(new Object[]{
                p.getIdPelanggan(), p.getNamaPelanggan(), p.getNoTelp(), p.getAlamat()
            });
        }
    }
}