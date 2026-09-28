#include "Konsumsi.cpp"
#include "Makanan.cpp"
#include "Minuman.cpp"

// multiple inheritance: Menu mewarisi dua class sekaligus (Makanan & Minuman).
// karena keduanya mewarisi Konsumsi secara virtual, Menu wajib
// menginisialisasi Konsumsi() sendiri secara langsung.
class Menu : public Makanan, public Minuman{
    private :
        int idMenu;
        bool rekomen;
        double harga;
    public :
        // constructor kosong
        Menu(){
            idMenu = 0;
            rekomen = false;
            harga = 0.0;
        };
        // constructor berparameter (memanggil constructor Konsumsi, Makanan, dan Minuman)
        Menu(int idMenu, string nama, string rasa, string alergen,
             string kategori, string suhuPenyajian, bool rekomen, double harga)
            : Konsumsi(nama, rasa, alergen),
              Makanan(nama, rasa, alergen, kategori),
              Minuman(nama, rasa, alergen, suhuPenyajian){
            this->idMenu = idMenu;
            this->rekomen = rekomen;
            this->harga = harga;
        }
        // getter and setter idMenu
        void setIdMenu(int idMenu){this->idMenu=idMenu;}
        int getIdMenu(){return idMenu;}

        // getter and setter rekomen
        void setRekomen(bool rekomen){this->rekomen=rekomen;}
        bool getRekomen(){return rekomen;}

        // getter and setter harga
        void setHarga(double harga){this->harga=harga;}
        double getHarga(){return harga;}

        // destructor
        ~Menu(){}
};
