import java.util.ArrayList;
import java.util.List;

public class Warkop {
    private String nama;
    private String jamBuka;
    private String jamTutup;
    private String alamat;
    private final Dapur dapur = new Dapur();                      // komposisi: dibuat bersama Warkop, tidak bisa lepas darinya
    private List<Pegawai> daftarPegawai = new ArrayList<>();         // agregasi
    private List<Menu> daftarMenu = new ArrayList<>();               // agregasi
    private List<Pembeli> daftarPembeli = new ArrayList<>();         // asosiasi (pembeli yang sedang membeli di warkop)
    private List<Distributor> daftarDistributor = new ArrayList<>(); // asosiasi (distributor langganan warkop)

    // constructor kosong
    public Warkop() {
        nama = "";
        jamBuka = "";
        jamTutup = "";
        alamat = "";
    }

    // constructor berparameter (dapur dibuat otomatis lewat komposisi)
    public Warkop(String nama, String jamBuka, String jamTutup, String alamat,
                  double luasDapur, int kapasitasMemasak) {
        this.nama = nama;
        this.jamBuka = jamBuka;
        this.jamTutup = jamTutup;
        this.alamat = alamat;
        dapur.setLuasDapur(luasDapur);
        dapur.setKapasitasMemasak(kapasitasMemasak);
    }

    // getter and setter nama
    public void setNama(String nama) { this.nama = nama; }
    public String getNama() { return nama; }

    // getter and setter jamBuka
    public void setJamBuka(String jamBuka) { this.jamBuka = jamBuka; }
    public String getJamBuka() { return jamBuka; }

    // getter and setter jamTutup
    public void setJamTutup(String jamTutup) { this.jamTutup = jamTutup; }
    public String getJamTutup() { return jamTutup; }

    // getter and setter alamat
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getAlamat() { return alamat; }

    // getter dapur dan semua daftar
    public Dapur getDapur() { return dapur; }
    public List<Pegawai> getDaftarPegawai() { return daftarPegawai; }
    public List<Menu> getDaftarMenu() { return daftarMenu; }
    public List<Pembeli> getDaftarPembeli() { return daftarPembeli; }
    public List<Distributor> getDaftarDistributor() { return daftarDistributor; }

    // penambahan data (objek dibuat di luar, lalu didaftarkan)
    public void tambahPegawai(Pegawai p) { daftarPegawai.add(p); }
    public void tambahMenu(Menu m) { daftarMenu.add(m); }
    public void tambahPembeli(Pembeli p) { daftarPembeli.add(p); }
    public void tambahDistributor(Distributor d) { daftarDistributor.add(d); }
}
