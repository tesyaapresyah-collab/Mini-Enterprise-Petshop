package view;

import entity.Hewan;
import entity.Pelanggan;
import repository.HewanRepository;
import repository.PelangganRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HewanView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private HewanRepository hewanRepo;
    private PelangganRepository pelangganRepo;
    
    private JTextField txtNamaHewan, txtJenis;
    private JComboBox<Pelanggan> cbPelanggan;

    public HewanView() {
        hewanRepo = new HewanRepository();
        pelangganRepo = new PelangganRepository();
        
        setTitle("Manajemen Data Hewan");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL INPUT ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Hewan"));
        
        txtNamaHewan = new JTextField();
        txtJenis = new JTextField();
        cbPelanggan = new JComboBox<>();
        loadPelangganToCombo();

        JButton btnTambah = new JButton("Tambah Hewan");
        JButton btnHapus = new JButton("Hapus Hewan");
        btnHapus.setBackground(Color.RED);
        btnHapus.setForeground(Color.WHITE);

        inputPanel.add(new JLabel("Nama Hewan:")); inputPanel.add(txtNamaHewan);
        inputPanel.add(new JLabel("Jenis:")); inputPanel.add(txtJenis);
        inputPanel.add(new JLabel("Pemilik:")); inputPanel.add(cbPelanggan);
        inputPanel.add(btnTambah); inputPanel.add(btnHapus);

        // --- PANEL TABEL ---
        String[] columns = {"ID", "Nama Hewan", "Jenis", "Pemilik"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        // --- LOGIKA TAMBAH ---
        btnTambah.addActionListener(e -> {
            if (cbPelanggan.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Pilih pemilik!");
                return;
            }
            Pelanggan p = (Pelanggan) cbPelanggan.getSelectedItem();
            Hewan h = new Hewan(0, txtNamaHewan.getText(), txtJenis.getText(), p.getIdPelanggan());
            hewanRepo.add(h);
            
            txtNamaHewan.setText(""); 
            txtJenis.setText("");
            loadData();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambah!");
        });

        // --- LOGIKA HAPUS ---
        btnHapus.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
                return;
            }
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                hewanRepo.delete(id);
                loadData();
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();
    }

    private void loadPelangganToCombo() {
        List<Pelanggan> list = pelangganRepo.getAll();
        for (Pelanggan p : list) {
            cbPelanggan.addItem(p);
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Hewan> list = hewanRepo.getAllWithPelanggan();
        for (Hewan h : list) {
            tableModel.addRow(new Object[]{
                h.getIdHewan(), 
                h.getNamaHewan(), 
                h.getJenisHewan(), 
                h.getNamaPemilik()
            });
        }
    }
}