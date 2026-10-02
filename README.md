# Janji
Saya Faridchi Trianda Safaraz dengan NIM 2506827 mengerjakan Tugas Praktikum 3 pada Mata Kuliah Desain dan Pemrograman Berorientasi Objek (DPBO) untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

# Struktur File
```
Main
├── Cpp/
│   ├── Kode Program/
│   │   ├── AlatMasak.cpp
│   │   ├── AlatPenyaji.cpp
│   │   ├── Dapur.cpp
│   │   ├── Distributor.cpp
│   │   ├── Konsumsi.cpp
│   │   ├── Makanan.cpp
│   │   ├── Manusia.cpp
│   │   ├── Menu.cpp
│   │   ├── Minuman.cpp
│   │   ├── Pegawai.cpp
│   │   ├── Pembeli.cpp
│   │   ├── Peralatan.cpp
│   │   ├── Stok.cpp
│   │   ├── Warkop.cpp
│   │   └── main.cpp
│   │
│   └── Dokumentasi/
│       ├── Cpp1.png
│       ├── Cpp2.png
│       └── Cpp3.png
│
├── Java/
│   ├── Kode Program/
│   │   ├── AlatMasak.java
│   │   ├── AlatPenyaji.java
│   │   ├── Dapur.java
│   │   ├── Distributor.java
│   │   ├── Konsumsi.java
│   │   ├── Main.java
│   │   ├── Makanan.java
│   │   ├── Manusia.java
│   │   ├── Menu.java
│   │   ├── Minuman.java
│   │   ├── PembacaPerintah.java
│   │   ├── Pegawai.java
│   │   ├── Pembeli.java
│   │   ├── Peralatan.java
│   │   ├── Stok.java
│   │   └── Warkop.java
│   │
│   └── Dokumentasi/
│       ├── java1.png
│       ├── java2.png
│       └── java3.png
│
├── Python/
│   ├── Kode Program/
│   │   ├── AlatMasak.py
│   │   ├── AlatPenyaji.py
│   │   ├── Dapur.py
│   │   ├── Distributor.py
│   │   ├── Konsumsi.py
│   │   ├── main.py
│   │   ├── Makanan.py
│   │   ├── Manusia.py
│   │   ├── Menu.py
│   │   ├── Minuman.py
│   │   ├── Pegawai.py
│   │   ├── Pembeli.py
│   │   ├── Peralatan.py
│   │   ├── Stok.py
│   │   └── Warkop.py
│   │
│   └── Dokumentasi/
│       ├── Py1.png
│       ├── Py2.png
│       └── Py3.png
│
├── Diagram.png
└── README.md
```

# Diagram
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Diagram.png)

# Desain
Program mensimulasikan sistem **Warkop** dan mencakup **14** class, yaitu **Manusia**, **Pegawai**, **Pembeli**, **Distributor**, **Stok**, **Peralatan**, **AlatMasak**, **AlatPenyaji**, **Dapur**, **Konsumsi**, **Makanan**, **Minuman**, **Menu**, dan **Warkop**.
Desain yang telah saya buat menerapkan beberapa konsep Object-Oriented Programming (OOP), yaitu :

- **Inheritance**: `Pegawai`, `Pembeli`, & `Distributor` mewarisi `Manusia`, `AlatMasak` & `AlatPenyaji` mewarisi `Peralatan`, `Makanan` & `Minuman` mewarisi `Konsumsi`, dan `Menu` mewarisi `Makanan` & `Minuman`.
- **Composition**: Kelas `Warkop` memiliki relasi composition dengan `Dapur` (objek `Dapur` dibuat bersama `Warkop` dan ikut hilang ketika `Warkop` dihapus).
- **Aggregation**: Kelas `Warkop` memiliki relasi agregasi dengan `Pegawai` dan `Menu`, serta kelas `Dapur` memiliki relasi agregasi dengan `AlatMasak` dan `AlatPenyaji`.
- **Association**: Kelas `Warkop` berasosiasi dengan `Pembeli` dan `Distributor`, serta `Distributor` berasosiasi dengan `Stok` (stok bahan yang dipasok).
- **Array of Object**: `Warkop` menyimpan banyak `Pegawai`, `Menu`, `Pembeli`, dan `Distributor`, `Dapur` menyimpan banyak `AlatMasak` dan `AlatPenyaji`, serta `Distributor` menyimpan banyak `Stok`, masing-masing dalam bentuk list/array.
- **Hierarchical Inheritance**: `Pegawai`, `Pembeli`, & `Distributor` mewarisi `Manusia`, `AlatMasak` & `AlatPenyaji` mewarisi `Peralatan`, dan `Makanan` & `Minuman` mewarisi `Konsumsi`.
- **Multiple Inheritance**: `Menu` mewarisi `Makanan` dan `Minuman` sekaligus.
- **Hybrid Inheritance**: Kombinasi hierarchical dan multiple inheritance, yaitu `Konsumsi` → `Makanan` & `Minuman` → `Menu` (bentuk diamond). Pada C++ digunakan *virtual inheritance* agar `Konsumsi` tidak memiliki dua salinan.

**Catatan untuk versi Java :**

Terdapat beberapa tambahan untuk program Java jika dibandingkan dengan program C++ dan Python. yaitu :

- **Interface untuk menyelesaikan diamond inheritance.** `Konsumsi`, `Makanan`, dan `Minuman` dibuat sebagai **interface** (bukan class) karena Java tidak mendukung multiple inheritance antar class. `Menu` memakai `implements Makanan, Minuman`, dan seluruh atribut dari ketiga interface tersebut disimpan langsung di `Menu`. Pada C++ masalah ini diselesaikan dengan *virtual inheritance*, sedangkan pada Python dengan MRO.
- **Kelas tambahan `PembacaPerintah`.** C++ memakai `istringstream` untuk memecah dan membaca perintah yang diketik, sedangkan Java tidak punya padanan langsungnya. Karena itu dibuat kelas `PembacaPerintah` dengan method `kata()`, `bulat()`, dan `pecahan()` untuk membaca kata, bilangan bulat, dan bilangan desimal dari satu baris perintah. Kelas ini juga menandai kegagalan baca (misalnya input bukan angka) lewat method `gagal()`. Python tidak membutuhkannya karena cukup memakai `split()` dan `int()` / `float()`.

## Detail Kelas

### Manusia
- `nama`: Nama orang.
- `umur`: Umur orang.
- `jenisKelamin`: Jenis kelamin orang.

### Pegawai (Turunan dari Manusia)
- `jenisPekerjaan`: Jenis pekerjaan pegawai (Kasir, Barista, dll).
- `gaji`: Gaji pegawai.
- `shift`: Shift kerja pegawai (Pagi/Siang/Malam).

### Pembeli (Turunan dari Manusia)
- `referensi`: Masukan/referensi dari pembeli.
- `sumberInfo`: Sumber pembeli mengetahui warkop (Instagram, TikTok, dll).

### Distributor (Turunan dari Manusia)
- `idDistributor`: ID unik distributor.
- `perusahaan`: Nama perusahaan distributor.
- `jenisBarang`: Jenis barang yang didistribusikan.
- `daftarStok`: Daftar `Stok` yang dipasok distributor.

### Stok
- `namaBahan`: Nama bahan.
- `jumlah`: Jumlah stok.
- `satuan`: Satuan stok (kg, liter, kaleng, dll).

### Peralatan
- `namaBrand`: Merek peralatan.
- `kondisi`: Kondisi peralatan (baru/bekas).
- `bahan`: Bahan pembuat peralatan.

### AlatMasak (Turunan dari Peralatan)
- `berat`: Berat alat masak (kg).
- `jenis`: Jenis alat masak (Panci, Wajan, dll).

### AlatPenyaji (Turunan dari Peralatan)
- `jenis`: Jenis alat penyaji (Piring, Gelas, dll).
- `jumlah`: Jumlah alat penyaji.
- `ukuran`: Ukuran alat penyaji (Kecil/Sedang/Besar).

### Dapur
- `luasDapur`: Luas dapur (m²).
- `kapasitasMemasak`: Kapasitas memasak dapur.
- `daftarAlatMasak`: Daftar `AlatMasak` di dapur.
- `daftarAlatPenyaji`: Daftar `AlatPenyaji` di dapur.

### Konsumsi
- `nama`: Nama konsumsi.
- `rasa`: Rasa konsumsi.
- `alergen`: Alergen yang terkandung.

### Makanan (Turunan dari Konsumsi)
- `kategori`: Kategori makanan.

### Minuman (Turunan dari Konsumsi)
- `suhuPenyajian`: Suhu penyajian minuman (Panas/Dingin).

### Menu (Turunan dari Makanan dan Minuman)
- `idMenu`: ID unik menu.
- `rekomen`: Status menu rekomendasi.
- `harga`: Harga menu.

### Warkop
- `nama`: Nama warkop.
- `jamBuka`: Jam buka warkop.
- `jamTutup`: Jam tutup warkop.
- `alamat`: Alamat warkop.
- `dapur`: Dapur milik warkop (composition).
- `daftarPegawai`: Daftar `Pegawai` warkop.
- `daftarMenu`: Daftar `Menu` warkop.
- `daftarPembeli`: Daftar `Pembeli` warkop.
- `daftarDistributor`: Daftar `Distributor` langganan warkop.

## Perintah Program
Program berjalan interaktif lewat terminal. Gunakan tanda `_` sebagai pengganti spasi (contoh: `Nasi_Goreng`).

| Perintah | Format Penggunaan | Keterangan |
| --- | --- | --- |
| `add pegawai` | `add pegawai (nama) (umur) (kelamin) (pekerjaan) (gaji) (shift)` | Menambahkan satu pegawai. |
| `add pembeli` | `add pembeli (nama) (umur) (kelamin) (referensi) (sumberInfo)` | Menambahkan satu pembeli. |
| `add distributor` | `add distributor (id) (nama) (umur) (kelamin) (perusahaan) (jenisBarang)` | Menambahkan satu distributor (id tidak boleh kembar). |
| `add stok` | `add stok (idDistributor) (namaBahan) (jumlah) (satuan)` | Menambahkan stok ke distributor sesuai id. |
| `add alatmasak` | `add alatmasak (brand) (kondisi) (bahan) (berat) (jenis)` | Menambahkan satu alat masak ke dapur. |
| `add alatpenyaji` | `add alatpenyaji (brand) (kondisi) (bahan) (jenis) (jumlah) (ukuran)` | Menambahkan satu alat penyaji ke dapur. |
| `add menu` | `add menu (id) (nama) (rasa) (alergen) (kategori) (suhu) (rekomen y/n) (harga)` | Menambahkan satu menu (id tidak boleh kembar). |
| `show` | `show (profil/pegawai/menu/pembeli/distributor/stok/alatmasak/alatpenyaji)` | Menampilkan data dalam tabel dinamis. Tanpa tambahan = tampilkan semua. |
| `panduan` | `panduan` | Menampilkan panduan penggunaan. |
| `done` | `done` | Mengakhiri program. |

# Dokumentasi

## C++
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Cpp/Dokumentasi/Cpp1.png)
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Cpp/Dokumentasi/Cpp2.png)
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Cpp/Dokumentasi/Cpp3.png)

## JAVA
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Java/Dokumentasi/java1.png)
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Java/Dokumentasi/java2.png)
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Java/Dokumentasi/java3.png)

## PYTHON
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Python/Dokumentasi/Py1.png)
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Python/Dokumentasi/Py2.png)
![](https://github.com/safarazzz/TP3DPBO2526C2/raw/main/Python/Dokumentasi/Py3.png)