#include "Manusia.cpp"
#include "Pegawai.cpp"
#include "Pembeli.cpp"
#include "Distributor.cpp"
#include "Menu.cpp"
#include "Dapur.cpp"

class Warkop{
    private :
        string nama;
        string jamBuka;
        string jamTutup;
        string alamat;
        Dapur dapur;                            // komposisi: ikut dibuat dan ikut hancur bareng Warkop
        vector<Pegawai*> daftarPegawai;         // agregasi
        vector<Menu*> daftarMenu;               // agregasi
        vector<Pembeli*> daftarPembeli;         // asosiasi (pembeli yang sedang membeli di warkop)
        vector<Distributor*> daftarDistributor; // asosiasi (distributor langganan warkop)
    public :
        // constructor kosong
        Warkop(){
            nama = "";
            jamBuka = "";
            jamTutup = "";
            alamat = "";
        };
        // constructor berparameter (dapur dibuat otomatis lewat komposisi)
        Warkop(string nama, string jamBuka, string jamTutup, string alamat,
               double luasDapur, int kapasitasMemasak){
            this->nama = nama;
            this->jamBuka = jamBuka;
            this->jamTutup = jamTutup;
            this->alamat = alamat;
            dapur.setLuasDapur(luasDapur);
            dapur.setKapasitasMemasak(kapasitasMemasak);
        }
        // getter and setter nama
        void setNama(string nama){this->nama=nama;}
        string getNama(){return nama;}

        // getter and setter jamBuka
        void setJamBuka(string jamBuka){this->jamBuka=jamBuka;}
        string getJamBuka(){return jamBuka;}

        // getter and setter jamTutup
        void setJamTutup(string jamTutup){this->jamTutup=jamTutup;}
        string getJamTutup(){return jamTutup;}

        // getter and setter alamat
        void setAlamat(string alamat){this->alamat=alamat;}
        string getAlamat(){return alamat;}

        // getter dapur dan semua daftar
        Dapur& getDapur(){return dapur;}
        vector<Pegawai*>& getDaftarPegawai(){return daftarPegawai;}
        vector<Menu*>& getDaftarMenu(){return daftarMenu;}
        vector<Pembeli*>& getDaftarPembeli(){return daftarPembeli;}
        vector<Distributor*>& getDaftarDistributor(){return daftarDistributor;}

        // penambahan data (objek dibuat di luar, lalu didaftarkan)
        void tambahPegawai(Pegawai* p){daftarPegawai.push_back(p);}
        void tambahMenu(Menu* m){daftarMenu.push_back(m);}
        void tambahPembeli(Pembeli* p){daftarPembeli.push_back(p);}
        void tambahDistributor(Distributor* d){daftarDistributor.push_back(d);}

        // destructor (hapus semua data yang didaftarkan, mencegah memory leak)
        ~Warkop(){
            for (Pegawai* p : daftarPegawai) delete p;
            for (Menu* m : daftarMenu) delete m;
            for (Pembeli* p : daftarPembeli) delete p;
            for (Distributor* d : daftarDistributor) delete d;
        }
};
