// Manusia.cpp di-include lewat Warkop.cpp (parent dipakai bersama Pegawai, Pembeli, Distributor)
#include "Stok.cpp"

class Distributor : public Manusia{
    private :
        int idDistributor;
        string perusahaan;
        string jenisBarang;
        vector<Stok*> daftarStok; // asosiasi: stok bahan yang dipasok distributor ini
    public :
        // constructor kosong
        Distributor(){
            idDistributor = 0;
            perusahaan = "";
            jenisBarang = "";
        };
        // constructor berparameter (memanggil constructor Manusia)
        Distributor(string nama, int umur, string jenisKelamin,
                    int idDistributor, string perusahaan, string jenisBarang)
            : Manusia(nama, umur, jenisKelamin){
            this->idDistributor = idDistributor;
            this->perusahaan = perusahaan;
            this->jenisBarang = jenisBarang;
        }
        // getter and setter idDistributor
        void setIdDistributor(int idDistributor){this->idDistributor=idDistributor;}
        int getIdDistributor(){return idDistributor;}

        // getter and setter perusahaan
        void setPerusahaan(string perusahaan){this->perusahaan=perusahaan;}
        string getPerusahaan(){return perusahaan;}

        // getter and setter jenisBarang
        void setJenisBarang(string jenisBarang){this->jenisBarang=jenisBarang;}
        string getJenisBarang(){return jenisBarang;}

        // getter daftar stok dan penambahan stok (dibuat di luar, lalu didaftarkan)
        vector<Stok*>& getDaftarStok(){return daftarStok;}
        void tambahStok(Stok* s){daftarStok.push_back(s);}

        // destructor (hapus stok yang didaftarkan, mencegah memory leak)
        ~Distributor(){
            for (Stok* s : daftarStok) delete s;
        }
};
