#include <bits/stdc++.h>
using namespace std;

class Manusia{
    private :
        string nama;
        int umur;
        string jenisKelamin;
    public :
        // constructor kosong
        Manusia(){
            nama = "";
            umur = 0;
            jenisKelamin = "";
        };
        // constructor berparameter
        Manusia(string nama, int umur, string jenisKelamin){
            this->nama = nama;
            this->umur = umur;
            this->jenisKelamin = jenisKelamin;
        }
        // getter and setter nama
        void setNama(string nama){this->nama=nama;}
        string getNama(){return nama;}

        // getter and setter umur
        void setUmur(int umur){this->umur=umur;}
        int getUmur(){return umur;}

        // getter and setter jenisKelamin
        void setJenisKelamin(string jenisKelamin){this->jenisKelamin=jenisKelamin;}
        string getJenisKelamin(){return jenisKelamin;}

        // destructor (virtual karena kelas ini akan diturunkan)
        virtual ~Manusia(){}
};
