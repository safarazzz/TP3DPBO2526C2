// Makanan dan minuman menjadi interface, karena java tidak mendukung diamond inheritance
public interface Minuman extends Konsumsi {
    // getter and setter suhuPenyajian
    void setSuhuPenyajian(String suhuPenyajian);
    String getSuhuPenyajian();
}
