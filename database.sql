-- Membuat Database
CREATE DATABASE db_petshop;
USE db_petshop;

-- 1. Tabel Users (Untuk fitur Login & CRUD Pegawai/Admin)
CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- Akan diisi dengan password yang di-hash
    role ENUM('Admin', 'Kasir') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabel Pelanggan (CRUD Pelanggan)
CREATE TABLE pelanggan (
    id_pelanggan INT AUTO_INCREMENT PRIMARY KEY,
    nama_pelanggan VARCHAR(100) NOT NULL,
    no_telp VARCHAR(15) NOT NULL,
    alamat TEXT
);

-- 3. Tabel Hewan (CRUD Hewan - Berelasi dengan Pelanggan)
CREATE TABLE hewan (
    id_hewan INT AUTO_INCREMENT PRIMARY KEY,
    nama_hewan VARCHAR(50) NOT NULL,
    jenis_hewan VARCHAR(50) NOT NULL, -- Kucing, Anjing, Burung, dll
    id_pelanggan INT,
    FOREIGN KEY (id_pelanggan) REFERENCES pelanggan(id_pelanggan) ON DELETE CASCADE
);

-- 4. Tabel Kategori (CRUD Kategori untuk Produk/Layanan)
CREATE TABLE kategori (
    id_kategori INT AUTO_INCREMENT PRIMARY KEY,
    nama_kategori VARCHAR(50) NOT NULL
);

-- 5. Tabel Layanan / Produk (CRUD Layanan & Barang - Berelasi dengan Kategori)
CREATE TABLE item_petshop (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nama_item VARCHAR(100) NOT NULL,
    jenis ENUM('Barang', 'Layanan') NOT NULL,
    harga DECIMAL(10, 2) NOT NULL,
    stok INT DEFAULT 0, -- Jika layanan, stok bisa diabaikan/0
    id_kategori INT,
    FOREIGN KEY (id_kategori) REFERENCES kategori(id_kategori) ON DELETE SET NULL
);

-- 6. Tabel Transaksi (CRUD Transaksi - Header)
CREATE TABLE transaksi (
    id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
    tanggal_transaksi DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_pelanggan INT,
    id_user INT,
    total_bayar DECIMAL(10, 2) DEFAULT 0,
    FOREIGN KEY (id_pelanggan) REFERENCES pelanggan(id_pelanggan) ON DELETE CASCADE,
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE SET NULL
);

-- Tabel Detail Transaksi (Untuk relasi Many-to-Many antara Transaksi dan Item)
CREATE TABLE detail_transaksi (
    id_detail INT AUTO_INCREMENT PRIMARY KEY,
    id_transaksi INT,
    id_item INT,
    jumlah INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_transaksi) REFERENCES transaksi(id_transaksi) ON DELETE CASCADE,
    FOREIGN KEY (id_item) REFERENCES item_petshop(id_item) ON DELETE RESTRICT
);

-- Insert Data Dummy untuk Testing Login (Password admin123 sebelum di-hash)
INSERT INTO users (username, password, role) VALUES 
('admin', 'admin123', 'Admin');