package entity;

public class Pelanggan {
    private int idPelanggan;
    private String namaPelanggan;
    private String noTelp;
    private String alamat;

    public Pelanggan(int idPelanggan, String namaPelanggan, String noTelp, String alamat) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    // Getter
    public int getIdPelanggan() { return idPelanggan; }
    public String getNamaPelanggan() { return namaPelanggan; }
    public String getNoTelp() { return noTelp; }
    public String getAlamat() { return alamat; }

    // Method toString untuk JComboBox
    @Override
    public String toString() {
        return this.namaPelanggan;
    }
}