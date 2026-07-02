package entity;

public class Transaksi {
    private int idTransaksi;
    private int idPelanggan;
    private int idHewan;
    private int idProduk;
    private int jumlah;
    private int totalBayar;

    public Transaksi(int idTransaksi, int idPelanggan, int idHewan, int idProduk, int jumlah, int totalBayar) {
        this.idTransaksi = idTransaksi;
        this.idPelanggan = idPelanggan;
        this.idHewan = idHewan;
        this.idProduk = idProduk;
        this.jumlah = jumlah;
        this.totalBayar = totalBayar;
    }

    // Tambahkan semua Getter saja
    public int getIdTransaksi() { return idTransaksi; }
    public int getIdPelanggan() { return idPelanggan; }
    public int getIdHewan() { return idHewan; }
    public int getIdProduk() { return idProduk; }
    public int getJumlah() { return jumlah; }
    public int getTotalBayar() { return totalBayar; }
}