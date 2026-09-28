#include <bits/stdc++.h>
#include "Warkop.cpp"
using namespace std;

// deklarasi penyingkat dan warna
#define ln    '\n'
#define RED    "\033[31m"
#define BLUE   "\033[34m"
#define GREEN  "\033[32m"
#define RESET  "\033[0m"

// helper untuk mencetak garis pembatas tabel sesuai lebar tiap kolom
void printLine(const vector<int>& widths) {
    cout << "+";
    for (int w : widths) cout << string(w + 2, '-') << "+";
    cout << ln;
}

// helper untuk mencetak satu baris tabel sesuai lebar tiap kolom
void printRow(const vector<string>& cells, const vector<int>& widths) {
    cout << "|";
    for (size_t i = 0; i < cells.size(); i++) {
        cout << " " << left << setw(widths[i]) << cells[i] << " |";
    }
    cout << ln;
}

// mencetak tabel DINAMIS: lebar tiap kolom dihitung otomatis dari
// panjang data terpanjang di kolom tersebut (termasuk headernya),
// jadi tabel selalu rapi berapapun panjang data yang dimasukkan.
void printDynamicTable(const vector<string> &headers, const vector<vector<string>> &rows) {
    size_t jumlahKolom = headers.size();
    vector<int> width(jumlahKolom, 0);

    // lebar awal tiap kolom diambil dari panjang headernya
    for (size_t c = 0; c < jumlahKolom; c++) {
        width[c] = (int)headers[c].length();
    }

    // perbesar lebar kolom kalau ada data yang lebih panjang dari header
    for (const auto &row : rows) {
        for (size_t c = 0; c < jumlahKolom; c++) {
            if ((int)row[c].length() > width[c]) {
                width[c] = (int)row[c].length();
            }
        }
    }

    printLine(width);
    printRow(headers, width);
    printLine(width);
    for (const auto &row : rows) {
        printRow(row, width);
    }
    printLine(width);
}

// mengubah angka desimal menjadi string dengan satu angka di belakang koma
string desimal(double x) {
    ostringstream s;
    s << fixed << setprecision(1) << x;
    return s.str();
}

// mengubah tanda _ menjadi spasi supaya input satu kata bisa berisi spasi (contoh: Nasi_Goreng)
string rapikan(string s) {
    replace(s.begin(), s.end(), '_', ' ');
    return s;
}

// mengubah string menjadi huruf kecil semua
string kecil(string s) {
    transform(s.begin(), s.end(), s.begin(), ::tolower);
    return s;
}

// prosedur untuk menampilkan pesan kesalahan ketika suatu daftar masih kosong
void kosong(string namaDaftar) {
    cout << RED << "Daftar " << namaDaftar << " masih kosong!" << RESET << ln;
}

// prosedur untuk menampilkan pesan kesalahan format perintah
void formatSalah(string format) {
    cout << RED << "Format salah. Gunakan: " << format << RESET << ln;
}

// prosedur panduan untuk menampilkan daftar perintah yang tersedia
void panduan() {
    vector<string> headers = {"Perintah", "Format Penggunaan", "Keterangan"};
    vector<vector<string>> rows = {
        {"add pegawai",     "add pegawai (nama) (umur) (kelamin) (pekerjaan) (gaji) (shift)",                    "Menambahkan satu pegawai."},
        {"add pembeli",     "add pembeli (nama) (umur) (kelamin) (referensi) (sumberInfo)",                      "Menambahkan satu pembeli."},
        {"add distributor", "add distributor (nama) (umur) (kelamin) (perusahaan) (jenisBarang)",                "Menambahkan satu distributor."},
        {"add stok",        "add stok (noDistributor) (namaBahan) (jumlah) (satuan)",                            "Menambahkan stok ke distributor sesuai nomor di tabel distributor."},
        {"add alatmasak",   "add alatmasak (brand) (kondisi) (bahan) (berat) (jenis)",                           "Menambahkan satu alat masak ke dapur."},
        {"add alatpenyaji", "add alatpenyaji (brand) (kondisi) (bahan) (jenis) (jumlah) (ukuran)",               "Menambahkan satu alat penyaji ke dapur."},
        {"add menu",        "add menu (id) (nama) (rasa) (alergen) (kategori) (suhu) (rekomen y/n) (harga)",     "Menambahkan satu menu makanan/minuman."},
        {"show",            "show (profil/pegawai/menu/pembeli/distributor/stok/alatmasak/alatpenyaji)",         "Menampilkan data dalam tabel dinamis. Tanpa tambahan = tampilkan semua."},
        {"panduan",         "panduan",                                                                           "Menampilkan panduan ini."},
        {"done",            "done",                                                                              "Mengakhiri sesi program."}
    };
    cout << "Panduan penggunaan program Warkop" << ln;
    printDynamicTable(headers, rows);
    cout << "Catatan: pakai tanda _ sebagai pengganti spasi (contoh: Nasi_Goreng)." << ln;
    cout << ln;
}

// ================= prosedur add =================

void addPegawai(Warkop &w, istringstream &iss) {
    string nama, kelamin, pekerjaan, shift;
    int umur;
    double gaji;
    if (!(iss >> nama >> umur >> kelamin >> pekerjaan >> gaji >> shift)) {
        formatSalah("add pegawai (nama) (umur) (kelamin) (pekerjaan) (gaji) (shift)");
        return;
    }
    nama = rapikan(nama);
    w.tambahPegawai(new Pegawai(nama, umur, kelamin, rapikan(pekerjaan), gaji, rapikan(shift)));
    cout << GREEN << "Pegawai \"" << nama << "\" berhasil ditambahkan." << RESET << ln;
}

void addPembeli(Warkop &w, istringstream &iss) {
    string nama, kelamin, referensi, sumberInfo;
    int umur;
    if (!(iss >> nama >> umur >> kelamin >> referensi >> sumberInfo)) {
        formatSalah("add pembeli (nama) (umur) (kelamin) (referensi) (sumberInfo)");
        return;
    }
    nama = rapikan(nama);
    w.tambahPembeli(new Pembeli(nama, umur, kelamin, rapikan(referensi), rapikan(sumberInfo)));
    cout << GREEN << "Pembeli \"" << nama << "\" berhasil ditambahkan." << RESET << ln;
}

void addDistributor(Warkop &w, istringstream &iss) {
    string nama, kelamin, perusahaan, jenisBarang;
    int umur;
    if (!(iss >> nama >> umur >> kelamin >> perusahaan >> jenisBarang)) {
        formatSalah("add distributor (nama) (umur) (kelamin) (perusahaan) (jenisBarang)");
        return;
    }
    nama = rapikan(nama);
    w.tambahDistributor(new Distributor(nama, umur, kelamin, rapikan(perusahaan), rapikan(jenisBarang)));
    cout << GREEN << "Distributor \"" << nama << "\" berhasil ditambahkan." << RESET << ln;
}

void addStok(Warkop &w, istringstream &iss) {
    int no, jumlah;
    string namaBahan, satuan;
    if (!(iss >> no >> namaBahan >> jumlah >> satuan)) {
        formatSalah("add stok (noDistributor) (namaBahan) (jumlah) (satuan)");
        return;
    }
    vector<Distributor*> &daftar = w.getDaftarDistributor();
    if (daftar.empty()) {
        cout << RED << "Belum ada distributor. Tambahkan distributor dulu dengan 'add distributor'." << RESET << ln;
        return;
    }
    if (no < 1 || no > (int)daftar.size()) {
        cout << RED << "Nomor distributor tidak valid. Pilih 1 sampai " << daftar.size()
             << " (lihat nomor di 'show distributor')." << RESET << ln;
        return;
    }
    namaBahan = rapikan(namaBahan);
    daftar[no - 1]->tambahStok(new Stok(namaBahan, jumlah, rapikan(satuan)));
    cout << GREEN << "Stok \"" << namaBahan << "\" berhasil ditambahkan ke "
         << daftar[no - 1]->getPerusahaan() << "." << RESET << ln;
}

void addAlatMasak(Warkop &w, istringstream &iss) {
    string brand, kondisi, bahan, jenis;
    double berat;
    if (!(iss >> brand >> kondisi >> bahan >> berat >> jenis)) {
        formatSalah("add alatmasak (brand) (kondisi) (bahan) (berat) (jenis)");
        return;
    }
    brand = rapikan(brand);
    w.getDapur().tambahAlatMasak(new AlatMasak(brand, rapikan(kondisi), rapikan(bahan), berat, rapikan(jenis)));
    cout << GREEN << "Alat masak \"" << brand << "\" berhasil ditambahkan." << RESET << ln;
}

void addAlatPenyaji(Warkop &w, istringstream &iss) {
    string brand, kondisi, bahan, jenis, ukuran;
    int jumlah;
    if (!(iss >> brand >> kondisi >> bahan >> jenis >> jumlah >> ukuran)) {
        formatSalah("add alatpenyaji (brand) (kondisi) (bahan) (jenis) (jumlah) (ukuran)");
        return;
    }
    brand = rapikan(brand);
    w.getDapur().tambahAlatPenyaji(new AlatPenyaji(brand, rapikan(kondisi), rapikan(bahan), rapikan(jenis), jumlah, rapikan(ukuran)));
    cout << GREEN << "Alat penyaji \"" << brand << "\" berhasil ditambahkan." << RESET << ln;
}

void addMenu(Warkop &w, istringstream &iss) {
    int id;
    string nama, rasa, alergen, kategori, suhu, rekomen;
    double harga;
    if (!(iss >> id >> nama >> rasa >> alergen >> kategori >> suhu >> rekomen >> harga)) {
        formatSalah("add menu (id) (nama) (rasa) (alergen) (kategori) (suhu) (rekomen y/n) (harga)");
        return;
    }
    nama = rapikan(nama);
    bool rek = (kecil(rekomen) == "y");
    w.tambahMenu(new Menu(id, nama, rapikan(rasa), rapikan(alergen), rapikan(kategori), rapikan(suhu), rek, harga));
    cout << GREEN << "Menu \"" << nama << "\" berhasil ditambahkan dengan id " << id << "." << RESET << ln;
}

// prosedur untuk menambahkan data baru berdasarkan jenis yang diketik setelah 'add'
void add(Warkop &w, istringstream &iss) {
    string jenis;
    if (!(iss >> jenis)) {
        cout << RED << "Format salah. Ketik 'panduan' untuk melihat format perintah add." << RESET << ln;
        return;
    }
    jenis = kecil(jenis);
    if (jenis == "pegawai") addPegawai(w, iss);
    else if (jenis == "pembeli") addPembeli(w, iss);
    else if (jenis == "distributor") addDistributor(w, iss);
    else if (jenis == "stok") addStok(w, iss);
    else if (jenis == "alatmasak") addAlatMasak(w, iss);
    else if (jenis == "alatpenyaji") addAlatPenyaji(w, iss);
    else if (jenis == "menu") addMenu(w, iss);
    else cout << RED << "Jenis data \"" << jenis << "\" tidak dikenali. Ketik 'panduan' untuk melihat daftar." << RESET << ln;
}

// ================= prosedur show =================

void showProfil(Warkop &w) {
    vector<string> headers = {"Nama", "Alamat", "Jam Buka", "Jam Tutup", "Luas Dapur (m2)", "Kapasitas Masak"};
    vector<vector<string>> rows;
    rows.push_back({w.getNama(), w.getAlamat(), w.getJamBuka(), w.getJamTutup(),
                    desimal(w.getDapur().getLuasDapur()), to_string(w.getDapur().getKapasitasMemasak())});
    cout << "Profil Warkop:" << ln;
    printDynamicTable(headers, rows);
}

void showPegawai(Warkop &w) {
    vector<Pegawai*> &daftar = w.getDaftarPegawai();
    if (daftar.empty()) { kosong("pegawai"); return; }
    vector<string> headers = {"Nama", "Umur", "Kelamin", "Pekerjaan", "Gaji", "Shift"};
    vector<vector<string>> rows;
    for (Pegawai* p : daftar) {
        rows.push_back({p->getNama(), to_string(p->getUmur()), p->getJenisKelamin(),
                        p->getJenisPekerjaan(), to_string((long long)p->getGaji()), p->getShift()});
    }
    cout << "Daftar Pegawai:" << ln;
    printDynamicTable(headers, rows);
    cout << "Total pegawai: " << daftar.size() << ln;
}

void showMenu(Warkop &w) {
    vector<Menu*> &daftar = w.getDaftarMenu();
    if (daftar.empty()) { kosong("menu"); return; }
    vector<string> headers = {"ID", "Nama", "Rasa", "Alergen", "Kategori", "Suhu", "Rekomen", "Harga"};
    vector<vector<string>> rows;
    for (Menu* m : daftar) {
        rows.push_back({to_string(m->getIdMenu()), m->getNama(), m->getRasa(), m->getAlergen(),
                        m->getKategori(), m->getSuhuPenyajian(), m->getRekomen() ? "Ya" : "Tidak",
                        to_string((long long)m->getHarga())});
    }
    cout << "Daftar Menu:" << ln;
    printDynamicTable(headers, rows);
    cout << "Total menu: " << daftar.size() << ln;
}

void showPembeli(Warkop &w) {
    vector<Pembeli*> &daftar = w.getDaftarPembeli();
    if (daftar.empty()) { kosong("pembeli"); return; }
    vector<string> headers = {"Nama", "Umur", "Kelamin", "Referensi", "SumberInfo"};
    vector<vector<string>> rows;
    for (Pembeli* p : daftar) {
        rows.push_back({p->getNama(), to_string(p->getUmur()), p->getJenisKelamin(),
                        p->getReferensi(), p->getSumberInfo()});
    }
    cout << "Daftar Pembeli:" << ln;
    printDynamicTable(headers, rows);
    cout << "Total pembeli: " << daftar.size() << ln;
}

void showDistributor(Warkop &w) {
    vector<Distributor*> &daftar = w.getDaftarDistributor();
    if (daftar.empty()) { kosong("distributor"); return; }
    vector<string> headers = {"No", "Nama", "Umur", "Kelamin", "Perusahaan", "JenisBarang"};
    vector<vector<string>> rows;
    for (size_t i = 0; i < daftar.size(); i++) {
        Distributor* d = daftar[i];
        rows.push_back({to_string(i + 1), d->getNama(), to_string(d->getUmur()), d->getJenisKelamin(),
                        d->getPerusahaan(), d->getJenisBarang()});
    }
    cout << "Daftar Distributor:" << ln;
    printDynamicTable(headers, rows);
    cout << "Total distributor: " << daftar.size() << ln;
}

// stok dari semua distributor digabung dalam satu tabel
void showStok(Warkop &w) {
    vector<string> headers = {"Distributor", "NamaBahan", "Jumlah", "Satuan"};
    vector<vector<string>> rows;
    for (Distributor* d : w.getDaftarDistributor()) {
        for (Stok* s : d->getDaftarStok()) {
            rows.push_back({d->getPerusahaan(), s->getNamaBahan(), to_string(s->getJumlah()), s->getSatuan()});
        }
    }
    if (rows.empty()) { kosong("stok bahan"); return; }
    cout << "Daftar Stok Bahan:" << ln;
    printDynamicTable(headers, rows);
    cout << "Total jenis stok: " << rows.size() << ln;
}

void showAlatMasak(Warkop &w) {
    vector<AlatMasak*> &daftar = w.getDapur().getDaftarAlatMasak();
    if (daftar.empty()) { kosong("alat masak"); return; }
    vector<string> headers = {"Brand", "Kondisi", "Bahan", "Berat", "Jenis"};
    vector<vector<string>> rows;
    for (AlatMasak* a : daftar) {
        rows.push_back({a->getNamaBrand(), a->getKondisi(), a->getBahan(),
                        desimal(a->getBerat()) + " kg", a->getJenis()});
    }
    cout << "Daftar Alat Masak:" << ln;
    printDynamicTable(headers, rows);
    cout << "Total alat masak: " << daftar.size() << ln;
}

void showAlatPenyaji(Warkop &w) {
    vector<AlatPenyaji*> &daftar = w.getDapur().getDaftarAlatPenyaji();
    if (daftar.empty()) { kosong("alat penyaji"); return; }
    vector<string> headers = {"Brand", "Kondisi", "Bahan", "Jenis", "Jumlah", "Ukuran"};
    vector<vector<string>> rows;
    for (AlatPenyaji* a : daftar) {
        rows.push_back({a->getNamaBrand(), a->getKondisi(), a->getBahan(),
                        a->getJenis(), to_string(a->getJumlah()), a->getUkuran()});
    }
    cout << "Daftar Alat Penyaji:" << ln;
    printDynamicTable(headers, rows);
    cout << "Total alat penyaji: " << daftar.size() << ln;
}

// prosedur untuk menampilkan data; tanpa argumen = tampilkan semua
void show(Warkop &w, istringstream &iss) {
    string target;
    if (!(iss >> target)) {
        showProfil(w);       cout << ln;
        showPegawai(w);      cout << ln;
        showMenu(w);         cout << ln;
        showPembeli(w);      cout << ln;
        showDistributor(w);  cout << ln;
        showStok(w);         cout << ln;
        showAlatMasak(w);    cout << ln;
        showAlatPenyaji(w);
        return;
    }
    target = kecil(target);
    if (target == "profil") showProfil(w);
    else if (target == "pegawai") showPegawai(w);
    else if (target == "menu") showMenu(w);
    else if (target == "pembeli") showPembeli(w);
    else if (target == "distributor") showDistributor(w);
    else if (target == "stok") showStok(w);
    else if (target == "alatmasak") showAlatMasak(w);
    else if (target == "alatpenyaji") showAlatPenyaji(w);
    else cout << RED << "Data \"" << target << "\" tidak dikenali. Ketik 'panduan' untuk melihat daftar." << RESET << ln;
}

int main() {
    cout << BLUE << "Selamat datang di Warkop kami!" << RESET << ln;
    cout << "(Ketik 'panduan' untuk menampilkan daftar perintah.)" << ln;

    Warkop w("Warkop Barokah", "07:00", "22:00", "Jl. Merdeka No. 10, Bandung", 20.0, 4);

    // data hardcode
    w.tambahPegawai(new Pegawai("Budi", 25, "L", "Kasir",  2500000, "Pagi"));
    w.tambahPegawai(new Pegawai("Siti", 22, "P", "Barista", 2700000, "Siang"));
    w.tambahPegawai(new Pegawai("Andi", 30, "L", "Koki",   3000000, "Malam"));

    w.tambahPembeli(new Pembeli("Rina", 20, "P", "Teman",       "Instagram"));
    w.tambahPembeli(new Pembeli("Joko", 35, "L", "Keluarga",    "Spanduk"));
    w.tambahPembeli(new Pembeli("Dewi", 28, "P", "Rekan Kerja", "TikTok"));

    // stok dibuat di luar, lalu didaftarkan ke distributor yang memasoknya
    Distributor* d1 = new Distributor("Pak Hasan", 45, "L", "PT Kopi Nusantara", "Kopi");
    d1->tambahStok(new Stok("Kopi Robusta", 20, "kg"));
    d1->tambahStok(new Stok("Gula Pasir",   15, "kg"));
    w.tambahDistributor(d1);

    Distributor* d2 = new Distributor("Bu Sari", 38, "P", "CV Susu Segar", "Susu");
    d2->tambahStok(new Stok("Susu Kental Manis", 30, "kaleng"));
    d2->tambahStok(new Stok("Susu UHT",          24, "liter"));
    w.tambahDistributor(d2);

    w.getDapur().tambahAlatMasak(new AlatMasak("Maspion", "baru",  "aluminium", 1.2, "Panci"));
    w.getDapur().tambahAlatMasak(new AlatMasak("Cosmos",  "bekas", "besi",      2.5, "Wajan"));
    w.getDapur().tambahAlatMasak(new AlatMasak("Philips", "baru",  "plastik",   1.8, "Rice Cooker"));

    w.getDapur().tambahAlatPenyaji(new AlatPenyaji("Lion Star",  "baru",  "plastik", "Piring", 24, "Sedang"));
    w.getDapur().tambahAlatPenyaji(new AlatPenyaji("Duralex",    "bekas", "kaca",    "Gelas",  30, "Kecil"));
    w.getDapur().tambahAlatPenyaji(new AlatPenyaji("Tupperware", "baru",  "plastik", "Nampan", 6,  "Besar"));

    w.tambahMenu(new Menu(1, "Nasi Goreng", "Gurih", "Telur",   "Makanan", "Panas",  true,  15000));
    w.tambahMenu(new Menu(2, "Kopi Susu",   "Manis", "Susu",    "Minuman", "Dingin", true,  12000));
    w.tambahMenu(new Menu(3, "Mie Rebus",   "Pedas", "Gluten",  "Makanan", "Panas",  false, 10000));
    w.tambahMenu(new Menu(4, "Es Teh",      "Manis", "-",       "Minuman", "Dingin", false, 5000));

    cout << BLUE << "Apa yang ingin anda lakukan hari ini?" << RESET << ln;

    string commandLine, input;
    bool masih = true;

    while (masih) {
        cout << "|| ";
        if (!getline(cin, commandLine)) break;
        istringstream iss(commandLine);
        if (!(iss >> input)) continue;
        input = kecil(input);

        // kalo done langsung berhenti (exit dari program)
        if (input == "done") {
            masih = false;
        } else {
            if (input == "add") {
                add(w, iss);
            } else if (input == "show") {
                show(w, iss);
            } else if (input == "panduan") {
                panduan();
            } else {
                cout << RED << "Perintah tidak dikenali. Ketik 'panduan' untuk melihat daftar perintah." << RESET << ln;
            }
            cout << BLUE << "Apakah ada yang ingin anda lakukan lagi?" << RESET << ln;
        }
    }

    cout << GREEN << "Terimakasih dan silahkan datang kembali!" << RESET << ln;
    return 0;
}
