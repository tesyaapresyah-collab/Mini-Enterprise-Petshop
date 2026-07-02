package repository;

import entity.Produk;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdukRepository {
    public List<Produk> getAll() {
        List<Produk> list = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Produk(rs.getInt("id_produk"), rs.getString("nama_produk"), 
                                    rs.getInt("harga"), rs.getInt("stok")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void add(Produk p) {
        String sql = "INSERT INTO produk (nama_produk, harga, stok) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNamaProduk());
            stmt.setInt(2, p.getHarga());
            stmt.setInt(3, p.getStok());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

   public void delete(int id) {
    String sql = "DELETE FROM produk WHERE id_produk = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}