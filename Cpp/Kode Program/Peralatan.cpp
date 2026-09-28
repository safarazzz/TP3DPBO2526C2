#include <bits/stdc++.h>
using namespace std;

class Peralatan{
    private :
        string namaBrand;
        string kondisi;
        string bahan;
    public :
        // constructor kosong
        Peralatan(){
            namaBrand = "";
            kondisi = "";
            bahan = "";
        };
        // constructor berparameter
        Peralatan(string namaBrand, string kondisi, string bahan){
            this->namaBrand = namaBrand;
            this->kondisi = kondisi;
            this->bahan = bahan;
        }
        // getter and setter namaBrand
        void setNamaBrand(string namaBrand){this->namaBrand=namaBrand;}
        string getNamaBrand(){return namaBrand;}

        // getter and setter kondisi
        void setKondisi(string kondisi){this->kondisi=kondisi;}
        string getKondisi(){return kondisi;}

        // getter and setter bahan
        void setBahan(string bahan){this->bahan=bahan;}
        string getBahan(){return bahan;}

        // destructor (virtual karena kelas ini akan diturunkan)
        virtual ~Peralatan(){}
};
