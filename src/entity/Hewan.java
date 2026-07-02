package entity;

public class Hewan {
    private int idHewan;
    private String namaHewan;
    private String jenisHewan;
    private int idPelanggan;
    private String namaPemilik;

    public Hewan(int idHewan, String namaHewan, String jenisHewan, int idPelanggan) {
        this.idHewan = idHewan;
        this.namaHewan = namaHewan;
        this.jenisHewan = jenisHewan;
        this.idPelanggan = idPelanggan;
    }

    public int getIdHewan() { return idHewan; }
    public String getNamaHewan() { return namaHewan; }
    public String getJenisHewan() { return jenisHewan; }
    public int getIdPelanggan() { return idPelanggan; }
    public String getNamaPemilik() { return namaPemilik; }
    public void setNamaPemilik(String namaPemilik) { this.namaPemilik = namaPemilik; }
}