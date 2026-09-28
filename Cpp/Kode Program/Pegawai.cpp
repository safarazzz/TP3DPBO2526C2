// Manusia.cpp di-include lewat Warkop.cpp (parent dipakai bersama Pegawai, Pembeli, Distributor)

class Pegawai : public Manusia{
    private :
        string jenisPekerjaan;
        double gaji;
        string shift;
    public :
        // constructor kosong
        Pegawai(){
            jenisPekerjaan = "";
            gaji = 0.0;
            shift = "";
        };
        // constructor berparameter (memanggil constructor Manusia)
        Pegawai(string nama, int umur, string jenisKelamin,
                string jenisPekerjaan, double gaji, string shift)
            : Manusia(nama, umur, jenisKelamin){
            this->jenisPekerjaan = jenisPekerjaan;
            this->gaji = gaji;
            this->shift = shift;
        }
        // getter and setter jenisPekerjaan
        void setJenisPekerjaan(string jenisPekerjaan){this->jenisPekerjaan=jenisPekerjaan;}
        string getJenisPekerjaan(){return jenisPekerjaan;}

        // getter and setter gaji
        void setGaji(double gaji){this->gaji=gaji;}
        double getGaji(){return gaji;}

        // getter and setter shift
        void setShift(string shift){this->shift=shift;}
        string getShift(){return shift;}

        // destructor
        ~Pegawai(){}
};
