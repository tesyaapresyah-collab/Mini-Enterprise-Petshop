package view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {
    setTitle("Dashboard - Petshop Management");
    setSize(800, 600); // Perbesar sedikit agar tombol muat
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    JLabel lblWelcome = new JLabel("Selamat Datang, Admin", JLabel.CENTER);
    lblWelcome.setFont(new Font("Arial", Font.BOLD, 22));
    add(lblWelcome, BorderLayout.NORTH);

    // Menggunakan GridLayout(3, 2) untuk 6 tombol
    JPanel menuPanel = new JPanel(new GridLayout(3, 2, 15, 15));
    menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JButton btnPelanggan = new JButton("CRUD Pelanggan");
    JButton btnHewan = new JButton("CRUD Hewan");
    JButton btnProduk = new JButton("CRUD Produk"); // Tombol yang belum muncul
    JButton btnTransaksi = new JButton("Transaksi");
    JButton btnLogout = new JButton("Logout");

    // Aksi tombol
    btnPelanggan.addActionListener(e -> new PelangganView().setVisible(true));
    btnHewan.addActionListener(e -> new HewanView().setVisible(true));
    btnProduk.addActionListener(e -> new ProdukView().setVisible(true)); // Tambahkan aksi ini
    btnLogout.addActionListener(e -> { dispose(); new LoginView().setVisible(true); });

    menuPanel.add(btnPelanggan);
    menuPanel.add(btnHewan);
    menuPanel.add(btnProduk);
    menuPanel.add(btnTransaksi);
    menuPanel.add(btnLogout);

    add(menuPanel, BorderLayout.CENTER);
}
}