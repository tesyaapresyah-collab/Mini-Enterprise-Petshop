package repository;

import entity.User;
import utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    
    // Method untuk mengecek kecocokan username dan password di database
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        // Menggunakan PreparedStatement untuk mencegah SQL Injection
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            
            // Jika data ditemukan, masukkan ke dalam objek Entity User
            if (rs.next()) {
                user = new User(
                    rs.getInt("id_user"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error Login DB: " + e.getMessage());
        }
        
        return user;
    }
}