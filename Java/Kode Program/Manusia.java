// Untuk di java, kelas manusia menjadi kelas abstract
public abstract class Manusia {
    private String nama;
    private int umur;
    private String jenisKelamin;

    // constructor kosong
    public Manusia() {
        nama = "";
        umur = 0;
        jenisKelamin = "";
    }

    // constructor berparameter
    public Manusia(String nama, int umur, String jenisKelamin) {
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
    }

    // getter and setter nama
    public void setNama(String nama) { this.nama = nama; }
    public String getNama() { return nama; }

    // getter and setter umur
    public void setUmur(int umur) { this.umur = umur; }
    public int getUmur() { return umur; }

    // getter and setter jenisKelamin
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
    public String getJenisKelamin() { return jenisKelamin; }
}
