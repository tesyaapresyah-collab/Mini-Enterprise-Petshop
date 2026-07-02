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
        
        // Pengaturan dasar jendela aplikasi (Frame)
        setTitle("Login - Petshop Mini Enterprise");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Agar jendela muncul tepat di tengah layar
        
        // Menggunakan padding dan layout grid sederhana
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Membuat komponen-komponen UI
        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        
        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        
        btnLogin = new JButton("Login");

        // Menambahkan komponen ke dalam panel
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel()); // Spacer / Ruang kosong
        panel.add(btnLogin);

        // Menambahkan panel utama ke dalam frame
        add(panel);

        // Menambahkan aksi ketika tombol Login diklik
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                // Cara mengambil teks dari JPasswordField
                String password = new String(txtPassword.getPassword());

                // Memanggil method handleLogin dari Controller
                if (userController.handleLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Berhasil!\nSelamat datang, " + UserController.currentUser.getUsername());
                    
                    // TODO: Buka form Dashboard Utama di sini nantinya
                    
                    dispose(); // Menutup form login setelah berhasil
                } else {
                    JOptionPane.showMessageDialog(null, "Login Gagal! Username atau Password salah.", "Error Autentikasi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}