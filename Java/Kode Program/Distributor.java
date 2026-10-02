import java.util.ArrayList;
import java.util.List;

public class Distributor extends Manusia {
    private int idDistributor;
    private String perusahaan;
    private String jenisBarang;
    private List<Stok> daftarStok = new ArrayList<>(); // asosiasi: stok bahan yang dipasok distributor ini

    // constructor kosong
    public Distributor() {
        super();
        idDistributor = 0;
        perusahaan = "";
        jenisBarang = "";
    }

    // constructor berparameter (memanggil constructor Manusia lewat super)
    public Distributor(String nama, int umur, String jenisKelamin,
                       int idDistributor, String perusahaan, String jenisBarang) {
        super(nama, umur, jenisKelamin);
        this.idDistributor = idDistributor;
        this.perusahaan = perusahaan;
        this.jenisBarang = jenisBarang;
    }

    // getter and setter idDistributor
    public void setIdDistributor(int idDistributor) { this.idDistributor = idDistributor; }
    public int getIdDistributor() { return idDistributor; }

    // getter and setter perusahaan
    public void setPerusahaan(String perusahaan) { this.perusahaan = perusahaan; }
    public String getPerusahaan() { return perusahaan; }

    // getter and setter jenisBarang
    public void setJenisBarang(String jenisBarang) { this.jenisBarang = jenisBarang; }
    public String getJenisBarang() { return jenisBarang; }

    // getter daftar stok dan penambahan stok (dibuat di luar, lalu didaftarkan)
    public List<Stok> getDaftarStok() { return daftarStok; }
    public void tambahStok(Stok s) { daftarStok.add(s); }
}
