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
        setTitle("Manajemen Data Produk");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL INPUT ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Produk"));
        
        txtNama = new JTextField();
        txtHarga = new JTextField();
        txtStok = new JTextField();
        
        JButton btnTambah = new JButton("Tambah Produk");
        JButton btnHapus = new JButton("Hapus Produk");
        btnHapus.setBackground(Color.RED);
        btnHapus.setForeground(Color.WHITE);

        inputPanel.add(new JLabel("Nama Produk:")); inputPanel.add(txtNama);
        inputPanel.add(new JLabel("Harga:")); inputPanel.add(txtHarga);
        inputPanel.add(new JLabel("Stok:")); inputPanel.add(txtStok);
        inputPanel.add(btnTambah); inputPanel.add(btnHapus);

        // --- PANEL TABEL ---
        String[] columns = {"ID", "Nama Produk", "Harga", "Stok"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        // --- LOGIKA TAMBAH ---
        btnTambah.addActionListener(e -> {
            try {
                String nama = txtNama.getText();
                int harga = Integer.parseInt(txtHarga.getText());
                int stok = Integer.parseInt(txtStok.getText());
                
                repo.add(new Produk(0, nama, harga, stok));
                
                txtNama.setText(""); txtHarga.setText(""); txtStok.setText("");
                loadData();
                JOptionPane.showMessageDialog(this, "Produk berhasil ditambah!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Harga dan Stok harus angka!");
            }
        });

        // --- LOGIKA HAPUS ---
        btnHapus.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
                return;
            }
            
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus produk ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                repo.delete(id);
                loadData();
                JOptionPane.showMessageDialog(this, "Produk berhasil dihapus!");
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Produk> list = repo.getAll();
        for (Produk p : list) {
            tableModel.addRow(new Object[]{
                p.getIdProduk(), p.getNamaProduk(), p.getHarga(), p.getStok()
            });
        }
    }
}