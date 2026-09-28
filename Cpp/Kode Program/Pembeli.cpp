// Manusia.cpp di-include lewat Warkop.cpp (parent dipakai bersama Pegawai, Pembeli, Distributor)

class Pembeli : public Manusia{
    private :
        string referensi;
        string sumberInfo;
    public :
        // constructor kosong
        Pembeli(){
            referensi = "";
            sumberInfo = "";
        };
        // constructor berparameter (memanggil constructor Manusia)
        Pembeli(string nama, int umur, string jenisKelamin,
                string referensi, string sumberInfo)
            : Manusia(nama, umur, jenisKelamin){
            this->referensi = referensi;
            this->sumberInfo = sumberInfo;
        }
        // getter and setter referensi
        void setReferensi(string referensi){this->referensi=referensi;}
        string getReferensi(){return referensi;}

        // getter and setter sumberInfo
        void setSumberInfo(string sumberInfo){this->sumberInfo=sumberInfo;}
        string getSumberInfo(){return sumberInfo;}

        // destructor
        ~Pembeli(){}
};
