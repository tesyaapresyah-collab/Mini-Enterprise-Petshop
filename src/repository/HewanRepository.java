package repository;

import entity.Hewan;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HewanRepository {

    public List<Hewan> getAllWithPelanggan() {
        List<Hewan> list = new ArrayList<>();
        String sql = "SELECT h.id_hewan, h.nama_hewan, h.jenis_hewan, h.id_pelanggan, p.nama_pelanggan " +
                     "FROM hewan h JOIN pelanggan p ON h.id_pelanggan = p.id_pelanggan";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Hewan h = new Hewan(
                    rs.getInt("id_hewan"),
                    rs.getString("nama_hewan"),
                    rs.getString("jenis_hewan"),
                    rs.getInt("id_pelanggan")
                );
                h.setNamaPemilik(rs.getString("nama_pelanggan"));
                list.add(h);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void add(Hewan h) {
        String sql = "INSERT INTO hewan (nama_hewan, jenis_hewan, id_pelanggan) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, h.getNamaHewan());
            stmt.setString(2, h.getJenisHewan());
            stmt.setInt(3, h.getIdPelanggan());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public void delete(int id) {
    String sql = "DELETE FROM hewan WHERE id_hewan = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error saat menghapus data hewan: " + e.getMessage());
    }
}
}