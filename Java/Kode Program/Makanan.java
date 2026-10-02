// Makanan dan minuman menjadi interface, karena java tidak mendukung diamond inheritance
public interface Makanan extends Konsumsi {
    // getter and setter kategori
    void setKategori(String kategori);
    String getKategori();
}
