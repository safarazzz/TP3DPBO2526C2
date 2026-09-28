// Peralatan.cpp di-include lewat Dapur.cpp (parent dipakai bersama AlatMasak dan AlatPenyaji)

class AlatMasak : public Peralatan{
    private :
        double berat;
        string jenis;
    public :
        // constructor kosong
        AlatMasak(){
            berat = 0.0;
            jenis = "";
        };
        // constructor berparameter (memanggil constructor Peralatan)
        AlatMasak(string namaBrand, string kondisi, string bahan,
                  double berat, string jenis)
            : Peralatan(namaBrand, kondisi, bahan){
            this->berat = berat;
            this->jenis = jenis;
        }
        // getter and setter berat
        void setBerat(double berat){this->berat=berat;}
        double getBerat(){return berat;}

        // getter and setter jenis
        void setJenis(string jenis){this->jenis=jenis;}
        string getJenis(){return jenis;}

        // destructor
        ~AlatMasak(){}
};
