// Peralatan.cpp di-include lewat Dapur.cpp (parent dipakai bersama AlatMasak dan AlatPenyaji)

class AlatPenyaji : public Peralatan{
    private :
        string jenis;
        int jumlah;
        string ukuran;
    public :
        // constructor kosong
        AlatPenyaji(){
            jenis = "";
            jumlah = 0;
            ukuran = "";
        };
        // constructor berparameter (memanggil constructor Peralatan)
        AlatPenyaji(string namaBrand, string kondisi, string bahan,
                    string jenis, int jumlah, string ukuran)
            : Peralatan(namaBrand, kondisi, bahan){
            this->jenis = jenis;
            this->jumlah = jumlah;
            this->ukuran = ukuran;
        }
        // getter and setter jenis
        void setJenis(string jenis){this->jenis=jenis;}
        string getJenis(){return jenis;}

        // getter and setter jumlah
        void setJumlah(int jumlah){this->jumlah=jumlah;}
        int getJumlah(){return jumlah;}

        // getter and setter ukuran
        void setUkuran(string ukuran){this->ukuran=ukuran;}
        string getUkuran(){return ukuran;}

        // destructor
        ~AlatPenyaji(){}
};
