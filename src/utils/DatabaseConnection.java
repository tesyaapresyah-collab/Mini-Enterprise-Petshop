package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_petshop";
    private static final String USER = "root"; // Default username XAMPP
    private static final String PASS = "";     // Default password XAMPP kosong
    private static Connection connection;

    // Menggunakan pola Singleton agar koneksi tidak menumpuk (mencegah memory leak)
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Pastikan Anda sudah menambahkan library MySQL Connector ke project Anda
                connection = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Koneksi Database Berhasil!");
            } catch (SQLException e) {
                System.out.println("Koneksi Database Gagal: " + e.getMessage());
            }
        }
        return connection;
    }
}