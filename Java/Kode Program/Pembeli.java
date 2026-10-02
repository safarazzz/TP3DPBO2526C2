public class Pembeli extends Manusia {
    private String referensi;
    private String sumberInfo;

    // constructor kosong
    public Pembeli() {
        super();
        referensi = "";
        sumberInfo = "";
    }

    // constructor berparameter (memanggil constructor Manusia lewat super)
    public Pembeli(String nama, int umur, String jenisKelamin,
                   String referensi, String sumberInfo) {
        super(nama, umur, jenisKelamin);
        this.referensi = referensi;
        this.sumberInfo = sumberInfo;
    }

    // getter and setter referensi
    public void setReferensi(String referensi) { this.referensi = referensi; }
    public String getReferensi() { return referensi; }

    // getter and setter sumberInfo
    public void setSumberInfo(String sumberInfo) { this.sumberInfo = sumberInfo; }
    public String getSumberInfo() { return sumberInfo; }
}
