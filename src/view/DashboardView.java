package view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {
    public DashboardView() {
        setTitle("Dashboard - Petshop Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel lblWelcome = new JLabel("Selamat Datang, Admin!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblWelcome);

        JButton btnPelanggan = new JButton("CRUD Pelanggan");
        // Aksi tombol akan kita arahkan ke form Pelanggan nanti
        btnPelanggan.addActionListener(e -> JOptionPane.showMessageDialog(this, "Fitur CRUD Pelanggan akan segera dibuat!"));
        
        add(btnPelanggan);
    }
}