public class Pegawai extends Manusia {
    private String jenisPekerjaan;
    private double gaji;
    private String shift;

    // constructor kosong
    public Pegawai() {
        super();
        jenisPekerjaan = "";
        gaji = 0.0;
        shift = "";
    }

    // constructor berparameter (memanggil constructor Manusia lewat super)
    public Pegawai(String nama, int umur, String jenisKelamin,
                   String jenisPekerjaan, double gaji, String shift) {
        super(nama, umur, jenisKelamin);
        this.jenisPekerjaan = jenisPekerjaan;
        this.gaji = gaji;
        this.shift = shift;
    }

    // getter and setter jenisPekerjaan
    public void setJenisPekerjaan(String jenisPekerjaan) { this.jenisPekerjaan = jenisPekerjaan; }
    public String getJenisPekerjaan() { return jenisPekerjaan; }

    // getter and setter gaji
    public void setGaji(double gaji) { this.gaji = gaji; }
    public double getGaji() { return gaji; }

    // getter and setter shift
    public void setShift(String shift) { this.shift = shift; }
    public String getShift() { return shift; }
}
