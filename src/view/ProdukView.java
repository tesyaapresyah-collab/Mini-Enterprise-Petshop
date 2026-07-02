package view;

import entity.Produk;
import repository.ProdukRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProdukView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ProdukRepository repo = new ProdukRepository();
    private JTextField txtNama, txtHarga, txtStok;

    public ProdukView() {
        setTitle("Manajemen Produk");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel Input
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        txtNama = new JTextField(); txtHarga = new JTextField(); txtStok = new JTextField();
        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");

        inputPanel.add(new JLabel("Nama:")); inputPanel.add(txtNama);
        inputPanel.add(new JLabel("Harga:")); inputPanel.add(txtHarga);
        inputPanel.add(new JLabel("Stok:")); inputPanel.add(txtStok);
        inputPanel.add(btnTambah); inputPanel.add(btnUpdate);
        inputPanel.add(btnHapus);

        // Tabel
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Harga", "Stok"}, 0);
        table = new JTable(tableModel);
        
        // --- LOGIKA MOUSE KLIK ---
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                txtNama.setText(tableModel.getValueAt(row, 1).toString());
                txtHarga.setText(tableModel.getValueAt(row, 2).toString());
                txtStok.setText(tableModel.getValueAt(row, 3).toString());
            }
        });

        // --- LOGIKA TAMBAH ---
        btnTambah.addActionListener(e -> {
            repo.add(new Produk(0, txtNama.getText(), Integer.parseInt(txtHarga.getText()), Integer.parseInt(txtStok.getText())));
            loadData();
            clearFields();
        });

        // --- LOGIKA UPDATE ---
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "Pilih data di tabel!"); return; }
            int id = (int) tableModel.getValueAt(row, 0);
            repo.update(new Produk(id, txtNama.getText(), Integer.parseInt(txtHarga.getText()), Integer.parseInt(txtStok.getText())));
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
        
        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Produk> list = repo.getAll();
        for (Produk p : list) {
            tableModel.addRow(new Object[]{p.getIdProduk(), p.getNamaProduk(), p.getHarga(), p.getStok()});
        }
    }
    
    private void clearFields() {
        txtNama.setText(""); txtHarga.setText(""); txtStok.setText("");
    }
}