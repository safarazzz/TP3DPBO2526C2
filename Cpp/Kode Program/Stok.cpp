#include <bits/stdc++.h>
using namespace std;

class Stok{
    private :
        string namaBahan;
        int jumlah;
        string satuan;
    public :
        // constructor kosong
        Stok(){
            namaBahan = "";
            jumlah = 0;
            satuan = "";
        };
        // constructor berparameter
        Stok(string namaBahan, int jumlah, string satuan){
            this->namaBahan = namaBahan;
            this->jumlah = jumlah;
            this->satuan = satuan;
        }
        // getter and setter namaBahan
        void setNamaBahan(string namaBahan){this->namaBahan=namaBahan;}
        string getNamaBahan(){return namaBahan;}

        // getter and setter jumlah
        void setJumlah(int jumlah){this->jumlah=jumlah;}
        int getJumlah(){return jumlah;}

        // getter and setter satuan
        void setSatuan(string satuan){this->satuan=satuan;}
        string getSatuan(){return satuan;}

        // destructor
        ~Stok(){}
};
