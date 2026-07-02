package view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {
        setTitle("Dashboard - Petshop Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel lblWelcome = new JLabel("Selamat Datang, Admin", JLabel.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblWelcome, BorderLayout.NORTH);

        // Panel Menu dengan Grid
        JPanel menuPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        // Inisialisasi Tombol
        JButton btnPelanggan = new JButton("CRUD Pelanggan");
        JButton btnHewan = new JButton("CRUD Hewan");
        JButton btnProduk = new JButton("CRUD Produk");
        JButton btnTransaksi = new JButton("Transaksi");
        JButton btnLogout = new JButton("Logout");

        // Styling Tombol (Opsional)
        Font btnFont = new Font("Arial", Font.PLAIN, 16);
        btnPelanggan.setFont(btnFont);
        btnHewan.setFont(btnFont);
        btnProduk.setFont(btnFont);
        btnTransaksi.setFont(btnFont);
        btnLogout.setFont(btnFont);
        btnLogout.setBackground(new Color(255, 100, 100)); // Warna merah untuk logout

        // Aksi Tombol
        btnPelanggan.addActionListener(e -> new PelangganView().setVisible(true));
        btnHewan.addActionListener(e -> new HewanView().setVisible(true));
        btnProduk.addActionListener(e -> new ProdukView().setVisible(true));
        
        // Aksi Logout
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                // Jika Anda memiliki LoginView, panggil di sini
                // new LoginView().setVisible(true);
                System.exit(0);
            }
        });

        // Menambahkan tombol ke panel
        menuPanel.add(btnPelanggan);
        menuPanel.add(btnHewan);
        menuPanel.add(btnProduk);
        menuPanel.add(btnTransaksi);
        menuPanel.add(btnLogout);

        add(menuPanel, BorderLayout.CENTER);
    }
}