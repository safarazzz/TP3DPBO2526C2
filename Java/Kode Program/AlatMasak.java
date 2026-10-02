public class AlatMasak extends Peralatan {
    private double berat;
    private String jenis;

    // constructor kosong
    public AlatMasak() {
        super();
        berat = 0.0;
        jenis = "";
    }

    // constructor berparameter (memanggil constructor Peralatan lewat super)
    public AlatMasak(String namaBrand, String kondisi, String bahan,
                     double berat, String jenis) {
        super(namaBrand, kondisi, bahan);
        this.berat = berat;
        this.jenis = jenis;
    }

    // getter and setter berat
    public void setBerat(double berat) { this.berat = berat; }
    public double getBerat() { return berat; }

    // getter and setter jenis
    public void setJenis(String jenis) { this.jenis = jenis; }
    public String getJenis() { return jenis; }
}
