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
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Pelanggan(rs.getInt("id_pelanggan"), rs.getString("nama_pelanggan"), 
                             rs.getString("no_telp"), rs.getString("alamat")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}