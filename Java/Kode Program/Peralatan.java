// kelas abstract: Peralatan tidak dibuat langsung, hanya diturunkan ke AlatMasak dan AlatPenyaji
public abstract class Peralatan {
    private String namaBrand;
    private String kondisi;
    private String bahan;

    // constructor kosong
    public Peralatan() {
        namaBrand = "";
        kondisi = "";
        bahan = "";
    }

    // constructor berparameter
    public Peralatan(String namaBrand, String kondisi, String bahan) {
        this.namaBrand = namaBrand;
        this.kondisi = kondisi;
        this.bahan = bahan;
    }

    // getter and setter namaBrand
    public void setNamaBrand(String namaBrand) { this.namaBrand = namaBrand; }
    public String getNamaBrand() { return namaBrand; }

    // getter and setter kondisi
    public void setKondisi(String kondisi) { this.kondisi = kondisi; }
    public String getKondisi() { return kondisi; }

    // getter and setter bahan
    public void setBahan(String bahan) { this.bahan = bahan; }
    public String getBahan() { return bahan; }
}
