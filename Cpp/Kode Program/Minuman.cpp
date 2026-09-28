// Konsumsi.cpp di-include lewat Menu.cpp (parent dipakai bersama Makanan dan Minuman)

class Minuman : virtual public Konsumsi{
    private :
        string suhuPenyajian;
    public :
        // constructor kosong
        Minuman(){
            suhuPenyajian = "";
        };
        // constructor berparameter (memanggil constructor Konsumsi)
        Minuman(string nama, string rasa, string alergen, string suhuPenyajian)
            : Konsumsi(nama, rasa, alergen){
            this->suhuPenyajian = suhuPenyajian;
        }
        // getter and setter suhuPenyajian
        void setSuhuPenyajian(string suhuPenyajian){this->suhuPenyajian=suhuPenyajian;}
        string getSuhuPenyajian(){return suhuPenyajian;}

        // destructor
        ~Minuman(){}
};
