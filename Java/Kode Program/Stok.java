public class Stok {
    private String namaBahan;
    private int jumlah;
    private String satuan;

    // constructor kosong
    public Stok() {
        namaBahan = "";
        jumlah = 0;
        satuan = "";
    }

    // constructor berparameter
    public Stok(String namaBahan, int jumlah, String satuan) {
        this.namaBahan = namaBahan;
        this.jumlah = jumlah;
        this.satuan = satuan;
    }

    // getter and setter namaBahan
    public void setNamaBahan(String namaBahan) { this.namaBahan = namaBahan; }
    public String getNamaBahan() { return namaBahan; }

    // getter and setter jumlah
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public int getJumlah() { return jumlah; }

    // getter and setter satuan
    public void setSatuan(String satuan) { this.satuan = satuan; }
    public String getSatuan() { return satuan; }
}
