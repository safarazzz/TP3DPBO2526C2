// multiple inheritance lewat interface: Menu mengimplementasikan Makanan & Minuman.
// Karena keduanya extends Konsumsi, hanya ada satu "kontrak" Konsumsi
public class Menu implements Makanan, Minuman {
    // atribut dari Konsumsi
    private String nama;
    private String rasa;
    private String alergen;
    // atribut dari Makanan
    private String kategori;
    // atribut dari Minuman
    private String suhuPenyajian;
    // atribut milik Menu sendiri
    private int idMenu;
    private boolean rekomen;
    private double harga;

    // constructor kosong
    public Menu() {
        nama = "";
        rasa = "";
        alergen = "";
        kategori = "";
        suhuPenyajian = "";
        idMenu = 0;
        rekomen = false;
        harga = 0.0;
    }

    // constructor berparameter
    public Menu(int idMenu, String nama, String rasa, String alergen,
                String kategori, String suhuPenyajian, boolean rekomen, double harga) {
        this.nama = nama;
        this.rasa = rasa;
        this.alergen = alergen;
        this.kategori = kategori;
        this.suhuPenyajian = suhuPenyajian;
        this.idMenu = idMenu;
        this.rekomen = rekomen;
        this.harga = harga;
    }

    // --- dari Konsumsi ---
    @Override public void setNama(String nama) { this.nama = nama; }
    @Override public String getNama() { return nama; }

    @Override public void setRasa(String rasa) { this.rasa = rasa; }
    @Override public String getRasa() { return rasa; }

    @Override public void setAlergen(String alergen) { this.alergen = alergen; }
    @Override public String getAlergen() { return alergen; }

    // --- dari Makanan ---
    @Override public void setKategori(String kategori) { this.kategori = kategori; }
    @Override public String getKategori() { return kategori; }

    // --- dari Minuman ---
    @Override public void setSuhuPenyajian(String suhuPenyajian) { this.suhuPenyajian = suhuPenyajian; }
    @Override public String getSuhuPenyajian() { return suhuPenyajian; }

    // --- milik Menu sendiri ---
    public void setIdMenu(int idMenu) { this.idMenu = idMenu; }
    public int getIdMenu() { return idMenu; }

    public void setRekomen(boolean rekomen) { this.rekomen = rekomen; }
    public boolean getRekomen() { return rekomen; }

    public void setHarga(double harga) { this.harga = harga; }
    public double getHarga() { return harga; }
}
