package view;

import controller.UserController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private UserController userController;

    public LoginView() {
        // Inisialisasi controller
        userController = new UserController();
        
        // Pengaturan dasar Jendela
        setTitle("Login - Petshop Mini Enterprise");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Muncul di tengah layar
        
        // Panel utama dengan GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Membuat komponen
        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        
        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        
        btnLogin = new JButton("Login");

        // Menambahkan komponen ke panel
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel()); // Spacer kosong
        panel.add(btnLogin);

        // Menambahkan panel ke frame
        add(panel);

        // Aksi Tombol Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                // Memanggil logika login dari Controller
                if (userController.handleLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Berhasil!\nSelamat datang, " + UserController.currentUser.getUsername());
                    
                    // Membuka Dashboard dan menutup Login
                    new DashboardView().setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Login Gagal! Periksa kembali Username dan Password Anda.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}