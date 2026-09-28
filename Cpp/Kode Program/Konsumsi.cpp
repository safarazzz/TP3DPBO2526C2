#include <bits/stdc++.h>
using namespace std;

class Konsumsi{
    private :
        string nama;
        string rasa;
        string alergen;
    public :
        // constructor kosong (wajib ada: Menu memanggil Konsumsi() langsung karena virtual base)
        Konsumsi(){
            nama = "";
            rasa = "";
            alergen = "";
        };
        // constructor berparameter
        Konsumsi(string nama, string rasa, string alergen){
            this->nama = nama;
            this->rasa = rasa;
            this->alergen = alergen;
        }
        // getter and setter nama
        void setNama(string nama){this->nama=nama;}
        string getNama(){return nama;}

        // getter and setter rasa
        void setRasa(string rasa){this->rasa=rasa;}
        string getRasa(){return rasa;}

        // getter and setter alergen
        void setAlergen(string alergen){this->alergen=alergen;}
        string getAlergen(){return alergen;}

        // destructor (virtual karena kelas ini akan diturunkan)
        virtual ~Konsumsi(){}
};
