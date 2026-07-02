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
    private HewanRepository hewanRepo = new HewanRepository();
    private PelangganRepository pelangganRepo = new PelangganRepository();
    
    private JTextField txtNamaHewan, txtJenis;
    private JComboBox<Pelanggan> cbPelanggan;

    public HewanView() {
        setTitle("Manajemen Data Hewan");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel Input
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Hewan"));
        
        txtNamaHewan = new JTextField();
        txtJenis = new JTextField();
        cbPelanggan = new JComboBox<>();
        loadPelangganToCombo();

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        btnHapus.setBackground(Color.RED);
        btnHapus.setForeground(Color.WHITE);

        inputPanel.add(new JLabel("Nama Hewan:")); inputPanel.add(txtNamaHewan);
        inputPanel.add(new JLabel("Jenis:")); inputPanel.add(txtJenis);
        inputPanel.add(new JLabel("Pemilik:")); inputPanel.add(cbPelanggan);
        inputPanel.add(btnTambah); inputPanel.add(btnUpdate);
        inputPanel.add(btnHapus);

        // Tabel
        String[] columns = {"ID", "Nama Hewan", "Jenis", "Pemilik"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        // Mouse Listener untuk pilih data
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                txtNamaHewan.setText(tableModel.getValueAt(row, 1).toString());
                txtJenis.setText(tableModel.getValueAt(row, 2).toString());
            }
        });

        // Logika Tambah
        btnTambah.addActionListener(e -> {
            Pelanggan p = (Pelanggan) cbPelanggan.getSelectedItem();
            hewanRepo.add(new Hewan(0, txtNamaHewan.getText(), txtJenis.getText(), p.getIdPelanggan()));
            loadData();
            clearFields();
        });

        // Logika Update
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;
            int id = (int) tableModel.getValueAt(row, 0);
            Pelanggan p = (Pelanggan) cbPelanggan.getSelectedItem();
            hewanRepo.update(new Hewan(id, txtNamaHewan.getText(), txtJenis.getText(), p.getIdPelanggan()));
            loadData();
            clearFields();
        });

        // Logika Hapus
        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;
            hewanRepo.delete((int) tableModel.getValueAt(row, 0));
            loadData();
            clearFields();
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        loadData();
    }

    private void loadPelangganToCombo() {
        for (Pelanggan p : pelangganRepo.getAll()) {
            cbPelanggan.addItem(p);
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        for (Hewan h : hewanRepo.getAllWithPelanggan()) {
            tableModel.addRow(new Object[]{h.getIdHewan(), h.getNamaHewan(), h.getJenisHewan(), h.getNamaPemilik()});
        }
    }

    private void clearFields() {
        txtNamaHewan.setText(""); txtJenis.setText("");
    }
}