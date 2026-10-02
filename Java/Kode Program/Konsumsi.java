// interface: Java tidak mendukung multiple inheritance antar class,
// jadi Konsumsi, Makanan, dan Minuman dibuat sebagai interface supaya
// Menu tetap bisa "mewarisi" Makanan sekaligus Minuman (diamond inheritance).
public interface Konsumsi {
    // getter and setter nama
    void setNama(String nama);
    String getNama();

    // getter and setter rasa
    void setRasa(String rasa);
    String getRasa();

    // getter and setter alergen
    void setAlergen(String alergen);
    String getAlergen();
}
