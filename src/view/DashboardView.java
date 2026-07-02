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

        // Di dalam konstruktor DashboardView
JButton btnPelanggan = new JButton("CRUD Pelanggan");
btnPelanggan.addActionListener(e -> new PelangganView().setVisible(true));
add(btnPelanggan); 

}
    }
