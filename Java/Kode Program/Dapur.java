import java.util.ArrayList;
import java.util.List;

public class Dapur {
    private double luasDapur;
    private int kapasitasMemasak;
    private List<AlatMasak> daftarAlatMasak = new ArrayList<>();     // agregasi
    private List<AlatPenyaji> daftarAlatPenyaji = new ArrayList<>(); // agregasi

    // constructor kosong
    public Dapur() {
        luasDapur = 0.0;
        kapasitasMemasak = 0;
    }

    // constructor berparameter
    public Dapur(double luasDapur, int kapasitasMemasak) {
        this.luasDapur = luasDapur;
        this.kapasitasMemasak = kapasitasMemasak;
    }

    // getter and setter luasDapur
    public void setLuasDapur(double luasDapur) { this.luasDapur = luasDapur; }
    public double getLuasDapur() { return luasDapur; }

    // getter and setter kapasitasMemasak
    public void setKapasitasMemasak(int kapasitasMemasak) { this.kapasitasMemasak = kapasitasMemasak; }
    public int getKapasitasMemasak() { return kapasitasMemasak; }

    // getter daftar alat dan penambahan alat (dibuat di luar, lalu didaftarkan)
    public List<AlatMasak> getDaftarAlatMasak() { return daftarAlatMasak; }
    public List<AlatPenyaji> getDaftarAlatPenyaji() { return daftarAlatPenyaji; }
    public void tambahAlatMasak(AlatMasak alat) { daftarAlatMasak.add(alat); }
    public void tambahAlatPenyaji(AlatPenyaji alat) { daftarAlatPenyaji.add(alat); }
}
