// Konsumsi.cpp di-include lewat Menu.cpp (parent dipakai bersama Makanan dan Minuman)

// virtual: supaya saat Menu mewarisi Makanan + Minuman, Konsumsi
// cuma ada satu salinan (mengatasi diamond inheritance problem)
class Makanan : virtual public Konsumsi{
    private :
        string kategori;
    public :
        // constructor kosong
        Makanan(){
            kategori = "";
        };
        // constructor berparameter (memanggil constructor Konsumsi)
        Makanan(string nama, string rasa, string alergen, string kategori)
            : Konsumsi(nama, rasa, alergen){
            this->kategori = kategori;
        }
        // getter and setter kategori
        void setKategori(string kategori){this->kategori=kategori;}
        string getKategori(){return kategori;}

        // destructor
        ~Makanan(){}
};
