package repository;

import entity.Pelanggan;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganRepository {

    // 1. Mengambil semua data pelanggan dari database
    public List<Pelanggan> getAll() {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pelanggan p = new Pelanggan(
                    rs.getInt("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("no_telp"),
                    rs.getString("alamat")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil data pelanggan: " + e.getMessage());
        }
        return list;
    }

    // 2. Menambah data pelanggan baru ke database
    public void add(Pelanggan p) {
        String sql = "INSERT INTO pelanggan (nama_pelanggan, no_telp, alamat) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, p.getNamaPelanggan());
            stmt.setString(2, p.getNoTelp());
            stmt.setString(3, p.getAlamat());
            
            stmt.executeUpdate();
            System.out.println("Data pelanggan berhasil ditambahkan.");
            
        } catch (SQLException e) {
            System.err.println("Error saat menambah data pelanggan: " + e.getMessage());
        }
    }
}