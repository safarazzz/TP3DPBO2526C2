public class AlatPenyaji extends Peralatan {
    private String jenis;
    private int jumlah;
    private String ukuran;

    // constructor kosong
    public AlatPenyaji() {
        super();
        jenis = "";
        jumlah = 0;
        ukuran = "";
    }

    // constructor berparameter (memanggil constructor Peralatan lewat super)
    public AlatPenyaji(String namaBrand, String kondisi, String bahan,
                       String jenis, int jumlah, String ukuran) {
        super(namaBrand, kondisi, bahan);
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.ukuran = ukuran;
    }

    // getter and setter jenis
    public void setJenis(String jenis) { this.jenis = jenis; }
    public String getJenis() { return jenis; }

    // getter and setter jumlah
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public int getJumlah() { return jumlah; }

    // getter and setter ukuran
    public void setUkuran(String ukuran) { this.ukuran = ukuran; }
    public String getUkuran() { return ukuran; }
}
