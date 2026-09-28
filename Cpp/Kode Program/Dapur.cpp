#include "Peralatan.cpp"
#include "AlatMasak.cpp"
#include "AlatPenyaji.cpp"

class Dapur{
    private :
        double luasDapur;
        int kapasitasMemasak;
        vector<AlatMasak*> daftarAlatMasak;     // agregasi
        vector<AlatPenyaji*> daftarAlatPenyaji; // agregasi
    public :
        // constructor kosong
        Dapur(){
            luasDapur = 0.0;
            kapasitasMemasak = 0;
        };
        // constructor berparameter
        Dapur(double luasDapur, int kapasitasMemasak){
            this->luasDapur = luasDapur;
            this->kapasitasMemasak = kapasitasMemasak;
        }
        // getter and setter luasDapur
        void setLuasDapur(double luasDapur){this->luasDapur=luasDapur;}
        double getLuasDapur(){return luasDapur;}

        // getter and setter kapasitasMemasak
        void setKapasitasMemasak(int kapasitasMemasak){this->kapasitasMemasak=kapasitasMemasak;}
        int getKapasitasMemasak(){return kapasitasMemasak;}

        // getter daftar alat dan penambahan alat (dibuat di luar, lalu didaftarkan)
        vector<AlatMasak*>& getDaftarAlatMasak(){return daftarAlatMasak;}
        vector<AlatPenyaji*>& getDaftarAlatPenyaji(){return daftarAlatPenyaji;}
        void tambahAlatMasak(AlatMasak* alat){daftarAlatMasak.push_back(alat);}
        void tambahAlatPenyaji(AlatPenyaji* alat){daftarAlatPenyaji.push_back(alat);}

        // destructor (hapus semua alat yang didaftarkan, mencegah memory leak)
        ~Dapur(){
            for (AlatMasak* a : daftarAlatMasak) delete a;
            for (AlatPenyaji* a : daftarAlatPenyaji) delete a;
        }
};
