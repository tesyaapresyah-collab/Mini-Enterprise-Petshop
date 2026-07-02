package repository;

import entity.Pelanggan;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganRepository {

    public List<Pelanggan> getAll() {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";
        
        // Koneksi dibuka di sini, dan otomatis ditutup saat blok try selesai
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(new Pelanggan(
                    rs.getInt("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("no_telp"),
                    rs.getString("alamat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void add(Pelanggan p) {
        String sql = "INSERT INTO pelanggan (nama_pelanggan, no_telp, alamat) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, p.getNamaPelanggan());
            stmt.setString(2, p.getNoTelp());
            stmt.setString(3, p.getAlamat());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {
    String sql = "DELETE FROM pelanggan WHERE id_pelanggan = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) { e.printStackTrace(); }
}
}