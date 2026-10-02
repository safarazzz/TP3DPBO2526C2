import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // deklarasi warna
    static final String RED = "\033[31m";
    static final String BLUE = "\033[34m";
    static final String GREEN = "\033[32m";
    static final String RESET = "\033[0m";
    static final String LN = "\n";

    // Input/output dibaca & ditulis per byte (ISO-8859-1) supaya panjang string dan
    // lebar kolom tabel dihitung per byte, persis seperti std::string di C++.
    static PrintStream out;

    // membaca satu baris dari stdin (pengganti getline di C++), null kalau sudah habis
    static String bacaBaris(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean adaIsi = false;
        int c;
        while ((c = in.read()) != -1) {
            adaIsi = true;
            if (c == '\n') return sb.toString();
            sb.append((char) c);
        }
        return adaIsi ? sb.toString() : null;
    }

    // mengulang satu karakter sebanyak n kali
    static String ulang(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }

    // helper untuk mencetak garis pembatas tabel sesuai lebar tiap kolom
    static void printLine(int[] widths) {
        out.print("+");
        for (int w : widths) out.print(ulang('-', w + 2) + "+");
        out.print(LN);
    }

    // helper untuk mencetak satu baris tabel sesuai lebar tiap kolom
    static void printRow(List<String> cells, int[] widths) {
        out.print("|");
        for (int i = 0; i < cells.size(); i++) {
            String cell = cells.get(i);
            out.print(" " + cell + ulang(' ', widths[i] - cell.length()) + " |");
        }
        out.print(LN);
    }

    // mencetak tabel DINAMIS: lebar tiap kolom dihitung otomatis dari
    // panjang data terpanjang di kolom tersebut (termasuk headernya),
    // jadi tabel selalu rapi berapapun panjang data yang dimasukkan.
    static void printDynamicTable(List<String> headers, List<List<String>> rows) {
        int jumlahKolom = headers.size();
        int[] width = new int[jumlahKolom];

        // lebar awal tiap kolom diambil dari panjang headernya
        for (int c = 0; c < jumlahKolom; c++) {
            width[c] = headers.get(c).length();
        }

        // perbesar lebar kolom kalau ada data yang lebih panjang dari header
        for (List<String> row : rows) {
            for (int c = 0; c < jumlahKolom; c++) {
                if (row.get(c).length() > width[c]) {
                    width[c] = row.get(c).length();
                }
            }
        }

        printLine(width);
        printRow(headers, width);
        printLine(width);
        for (List<String> row : rows) {
            printRow(row, width);
        }
        printLine(width);
    }

    // mengubah angka desimal menjadi string dengan satu angka di belakang koma
    // (pembulatan sama dengan printf/fixed di C++: dari nilai biner double yang sebenarnya)
    static String desimal(double x) {
        String hasil = new BigDecimal(x).setScale(1, RoundingMode.HALF_EVEN).toPlainString();
        if (Double.doubleToRawLongBits(x) < 0 && !hasil.startsWith("-")) hasil = "-" + hasil; // "-0.0"
        return hasil;
    }

    // cast double -> long long ala C++ (kalau di luar jangkauan hasilnya LLONG_MIN)
    static long keLong(double x) {
        if (Double.isNaN(x) || x >= 9.223372036854775807E18 || x < -9.223372036854775808E18) {
            return Long.MIN_VALUE;
        }
        return (long) x;
    }

    // mengubah tanda _ menjadi spasi supaya input satu kata bisa berisi spasi (contoh: Nasi_Goreng)
    static String rapikan(String s) {
        return s.replace('_', ' ');
    }

    // mengubah string menjadi huruf kecil semua (hanya A-Z, sama seperti tolower di C++)
    static String kecil(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') c = (char) (c + 32);
            sb.append(c);
        }
        return sb.toString();
    }

    // prosedur untuk menampilkan pesan kesalahan ketika suatu daftar masih kosong
    static void kosong(String namaDaftar) {
        out.print(RED + "Daftar " + namaDaftar + " masih kosong!" + RESET + LN);
    }

    // prosedur untuk menampilkan pesan kesalahan format perintah
    static void formatSalah(String format) {
        out.print(RED + "Format salah. Gunakan: " + format + RESET + LN);
    }

    // mencari distributor berdasarkan id, mengembalikan null kalau tidak ditemukan
    static Distributor cariDistributor(Warkop w, int id) {
        for (Distributor d : w.getDaftarDistributor()) {
            if (d.getIdDistributor() == id) return d;
        }
        return null;
    }

    // mencari menu berdasarkan id, mengembalikan null kalau tidak ditemukan
    static Menu cariMenu(Warkop w, int id) {
        for (Menu m : w.getDaftarMenu()) {
            if (m.getIdMenu() == id) return m;
        }
        return null;
    }

    // prosedur panduan untuk menampilkan daftar perintah yang tersedia
    static void panduan() {
        List<String> headers = Arrays.asList("Perintah", "Format Penggunaan", "Keterangan");
        List<List<String>> rows = Arrays.asList(
            Arrays.asList("add pegawai",     "add pegawai (nama) (umur) (kelamin) (pekerjaan) (gaji) (shift)",                    "Menambahkan satu pegawai."),
            Arrays.asList("add pembeli",     "add pembeli (nama) (umur) (kelamin) (referensi) (sumberInfo)",                      "Menambahkan satu pembeli."),
            Arrays.asList("add distributor", "add distributor (id) (nama) (umur) (kelamin) (perusahaan) (jenisBarang)",           "Menambahkan satu distributor (id tidak boleh kembar)."),
            Arrays.asList("add stok",        "add stok (idDistributor) (namaBahan) (jumlah) (satuan)",                            "Menambahkan stok ke distributor sesuai id di tabel distributor."),
            Arrays.asList("add alatmasak",   "add alatmasak (brand) (kondisi) (bahan) (berat) (jenis)",                           "Menambahkan satu alat masak ke dapur."),
            Arrays.asList("add alatpenyaji", "add alatpenyaji (brand) (kondisi) (bahan) (jenis) (jumlah) (ukuran)",               "Menambahkan satu alat penyaji ke dapur."),
            Arrays.asList("add menu",        "add menu (id) (nama) (rasa) (alergen) (kategori) (suhu) (rekomen y/n) (harga)",     "Menambahkan satu menu makanan/minuman (id tidak boleh kembar)."),
            Arrays.asList("show",            "show (profil/pegawai/menu/pembeli/distributor/stok/alatmasak/alatpenyaji)",         "Menampilkan data dalam tabel dinamis. Tanpa tambahan = tampilkan semua."),
            Arrays.asList("panduan",         "panduan",                                                                           "Menampilkan panduan ini."),
            Arrays.asList("done",            "done",                                                                              "Mengakhiri sesi program.")
        );
        out.print("Panduan penggunaan program Warkop" + LN);
        printDynamicTable(headers, rows);
        out.print("Catatan: pakai tanda _ sebagai pengganti spasi (contoh: Nasi_Goreng)." + LN);
        out.print(LN);
    }

    // ================= prosedur add =================

    static void addPegawai(Warkop w, PembacaPerintah iss) {
        String nama = iss.kata();
        int umur = iss.bulat();
        String kelamin = iss.kata();
        String pekerjaan = iss.kata();
        double gaji = iss.pecahan();
        String shift = iss.kata();
        if (iss.gagal()) {
            formatSalah("add pegawai (nama) (umur) (kelamin) (pekerjaan) (gaji) (shift)");
            return;
        }
        nama = rapikan(nama);
        w.tambahPegawai(new Pegawai(nama, umur, kelamin, rapikan(pekerjaan), gaji, rapikan(shift)));
        out.print(GREEN + "Pegawai \"" + nama + "\" berhasil ditambahkan." + RESET + LN);
    }

    static void addPembeli(Warkop w, PembacaPerintah iss) {
        String nama = iss.kata();
        int umur = iss.bulat();
        String kelamin = iss.kata();
        String referensi = iss.kata();
        String sumberInfo = iss.kata();
        if (iss.gagal()) {
            formatSalah("add pembeli (nama) (umur) (kelamin) (referensi) (sumberInfo)");
            return;
        }
        nama = rapikan(nama);
        w.tambahPembeli(new Pembeli(nama, umur, kelamin, rapikan(referensi), rapikan(sumberInfo)));
        out.print(GREEN + "Pembeli \"" + nama + "\" berhasil ditambahkan." + RESET + LN);
    }

    static void addDistributor(Warkop w, PembacaPerintah iss) {
        int id = iss.bulat();
        String nama = iss.kata();
        int umur = iss.bulat();
        String kelamin = iss.kata();
        String perusahaan = iss.kata();
        String jenisBarang = iss.kata();
        if (iss.gagal()) {
            formatSalah("add distributor (id) (nama) (umur) (kelamin) (perusahaan) (jenisBarang)");
            return;
        }
        Distributor ada = cariDistributor(w, id);
        if (ada != null) {
            out.print(RED + "Id distributor " + id + " sudah dipakai oleh " + ada.getNama()
                    + ". Gunakan id lain." + RESET + LN);
            return;
        }
        nama = rapikan(nama);
        w.tambahDistributor(new Distributor(nama, umur, kelamin, id, rapikan(perusahaan), rapikan(jenisBarang)));
        out.print(GREEN + "Distributor \"" + nama + "\" berhasil ditambahkan dengan id " + id + "." + RESET + LN);
    }

    static void addStok(Warkop w, PembacaPerintah iss) {
        int id = iss.bulat();
        String namaBahan = iss.kata();
        int jumlah = iss.bulat();
        String satuan = iss.kata();
        if (iss.gagal()) {
            formatSalah("add stok (idDistributor) (namaBahan) (jumlah) (satuan)");
            return;
        }
        if (w.getDaftarDistributor().isEmpty()) {
            out.print(RED + "Belum ada distributor. Tambahkan distributor dulu dengan 'add distributor'." + RESET + LN);
            return;
        }
        Distributor d = cariDistributor(w, id);
        if (d == null) {
            out.print(RED + "Distributor dengan id " + id + " tidak ditemukan (lihat id di 'show distributor')." + RESET + LN);
            return;
        }
        namaBahan = rapikan(namaBahan);
        d.tambahStok(new Stok(namaBahan, jumlah, rapikan(satuan)));
        out.print(GREEN + "Stok \"" + namaBahan + "\" berhasil ditambahkan ke "
                + d.getPerusahaan() + "." + RESET + LN);
    }

    static void addAlatMasak(Warkop w, PembacaPerintah iss) {
        String brand = iss.kata();
        String kondisi = iss.kata();
        String bahan = iss.kata();
        double berat = iss.pecahan();
        String jenis = iss.kata();
        if (iss.gagal()) {
            formatSalah("add alatmasak (brand) (kondisi) (bahan) (berat) (jenis)");
            return;
        }
        brand = rapikan(brand);
        w.getDapur().tambahAlatMasak(new AlatMasak(brand, rapikan(kondisi), rapikan(bahan), berat, rapikan(jenis)));
        out.print(GREEN + "Alat masak \"" + brand + "\" berhasil ditambahkan." + RESET + LN);
    }

    static void addAlatPenyaji(Warkop w, PembacaPerintah iss) {
        String brand = iss.kata();
        String kondisi = iss.kata();
        String bahan = iss.kata();
        String jenis = iss.kata();
        int jumlah = iss.bulat();
        String ukuran = iss.kata();
        if (iss.gagal()) {
            formatSalah("add alatpenyaji (brand) (kondisi) (bahan) (jenis) (jumlah) (ukuran)");
            return;
        }
        brand = rapikan(brand);
        w.getDapur().tambahAlatPenyaji(new AlatPenyaji(brand, rapikan(kondisi), rapikan(bahan), rapikan(jenis), jumlah, rapikan(ukuran)));
        out.print(GREEN + "Alat penyaji \"" + brand + "\" berhasil ditambahkan." + RESET + LN);
    }

    static void addMenu(Warkop w, PembacaPerintah iss) {
        int id = iss.bulat();
        String nama = iss.kata();
        String rasa = iss.kata();
        String alergen = iss.kata();
        String kategori = iss.kata();
        String suhu = iss.kata();
        String rekomen = iss.kata();
        double harga = iss.pecahan();
        if (iss.gagal()) {
            formatSalah("add menu (id) (nama) (rasa) (alergen) (kategori) (suhu) (rekomen y/n) (harga)");
            return;
        }
        Menu ada = cariMenu(w, id);
        if (ada != null) {
            out.print(RED + "Id menu " + id + " sudah dipakai oleh " + ada.getNama()
                    + ". Gunakan id lain." + RESET + LN);
            return;
        }
        nama = rapikan(nama);
        boolean rek = kecil(rekomen).equals("y");
        w.tambahMenu(new Menu(id, nama, rapikan(rasa), rapikan(alergen), rapikan(kategori), rapikan(suhu), rek, harga));
        out.print(GREEN + "Menu \"" + nama + "\" berhasil ditambahkan dengan id " + id + "." + RESET + LN);
    }

    // prosedur untuk menambahkan data baru berdasarkan jenis yang diketik setelah 'add'
    static void add(Warkop w, PembacaPerintah iss) {
        String jenis = iss.kata();
        if (iss.gagal()) {
            out.print(RED + "Format salah. Ketik 'panduan' untuk melihat format perintah add." + RESET + LN);
            return;
        }
        jenis = kecil(jenis);
        if (jenis.equals("pegawai")) addPegawai(w, iss);
        else if (jenis.equals("pembeli")) addPembeli(w, iss);
        else if (jenis.equals("distributor")) addDistributor(w, iss);
        else if (jenis.equals("stok")) addStok(w, iss);
        else if (jenis.equals("alatmasak")) addAlatMasak(w, iss);
        else if (jenis.equals("alatpenyaji")) addAlatPenyaji(w, iss);
        else if (jenis.equals("menu")) addMenu(w, iss);
        else out.print(RED + "Jenis data \"" + jenis + "\" tidak dikenali. Ketik 'panduan' untuk melihat daftar." + RESET + LN);
    }

    // ================= prosedur show =================

    static void showProfil(Warkop w) {
        List<String> headers = Arrays.asList("Nama", "Alamat", "Jam Buka", "Jam Tutup", "Luas Dapur (m2)", "Kapasitas Masak");
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(w.getNama(), w.getAlamat(), w.getJamBuka(), w.getJamTutup(),
                desimal(w.getDapur().getLuasDapur()), Integer.toString(w.getDapur().getKapasitasMemasak())));
        out.print("Profil Warkop:" + LN);
        printDynamicTable(headers, rows);
    }

    static void showPegawai(Warkop w) {
        List<Pegawai> daftar = w.getDaftarPegawai();
        if (daftar.isEmpty()) { kosong("pegawai"); return; }
        List<String> headers = Arrays.asList("Nama", "Umur", "Kelamin", "Pekerjaan", "Gaji", "Shift");
        List<List<String>> rows = new ArrayList<>();
        for (Pegawai p : daftar) {
            rows.add(Arrays.asList(p.getNama(), Integer.toString(p.getUmur()), p.getJenisKelamin(),
                    p.getJenisPekerjaan(), Long.toString(keLong(p.getGaji())), p.getShift()));
        }
        out.print("Daftar Pegawai:" + LN);
        printDynamicTable(headers, rows);
        out.print("Total pegawai: " + daftar.size() + LN);
    }

    static void showMenu(Warkop w) {
        List<Menu> daftar = w.getDaftarMenu();
        if (daftar.isEmpty()) { kosong("menu"); return; }
        List<String> headers = Arrays.asList("ID", "Nama", "Rasa", "Alergen", "Kategori", "Suhu", "Rekomen", "Harga");
        List<List<String>> rows = new ArrayList<>();
        for (Menu m : daftar) {
            rows.add(Arrays.asList(Integer.toString(m.getIdMenu()), m.getNama(), m.getRasa(), m.getAlergen(),
                    m.getKategori(), m.getSuhuPenyajian(), m.getRekomen() ? "Ya" : "Tidak",
                    Long.toString(keLong(m.getHarga()))));
        }
        out.print("Daftar Menu:" + LN);
        printDynamicTable(headers, rows);
        out.print("Total menu: " + daftar.size() + LN);
    }

    static void showPembeli(Warkop w) {
        List<Pembeli> daftar = w.getDaftarPembeli();
        if (daftar.isEmpty()) { kosong("pembeli"); return; }
        List<String> headers = Arrays.asList("Nama", "Umur", "Kelamin", "Referensi", "SumberInfo");
        List<List<String>> rows = new ArrayList<>();
        for (Pembeli p : daftar) {
            rows.add(Arrays.asList(p.getNama(), Integer.toString(p.getUmur()), p.getJenisKelamin(),
                    p.getReferensi(), p.getSumberInfo()));
        }
        out.print("Daftar Pembeli:" + LN);
        printDynamicTable(headers, rows);
        out.print("Total pembeli: " + daftar.size() + LN);
    }

    static void showDistributor(Warkop w) {
        List<Distributor> daftar = w.getDaftarDistributor();
        if (daftar.isEmpty()) { kosong("distributor"); return; }
        List<String> headers = Arrays.asList("ID", "Nama", "Umur", "Kelamin", "Perusahaan", "JenisBarang");
        List<List<String>> rows = new ArrayList<>();
        for (Distributor d : daftar) {
            rows.add(Arrays.asList(Integer.toString(d.getIdDistributor()), d.getNama(), Integer.toString(d.getUmur()),
                    d.getJenisKelamin(), d.getPerusahaan(), d.getJenisBarang()));
        }
        out.print("Daftar Distributor:" + LN);
        printDynamicTable(headers, rows);
        out.print("Total distributor: " + daftar.size() + LN);
    }

    // stok dari semua distributor digabung dalam satu tabel
    static void showStok(Warkop w) {
        List<String> headers = Arrays.asList("Distributor", "NamaBahan", "Jumlah", "Satuan");
        List<List<String>> rows = new ArrayList<>();
        for (Distributor d : w.getDaftarDistributor()) {
            for (Stok s : d.getDaftarStok()) {
                rows.add(Arrays.asList(d.getPerusahaan(), s.getNamaBahan(), Integer.toString(s.getJumlah()), s.getSatuan()));
            }
        }
        if (rows.isEmpty()) { kosong("stok bahan"); return; }
        out.print("Daftar Stok Bahan:" + LN);
        printDynamicTable(headers, rows);
        out.print("Total jenis stok: " + rows.size() + LN);
    }

    static void showAlatMasak(Warkop w) {
        List<AlatMasak> daftar = w.getDapur().getDaftarAlatMasak();
        if (daftar.isEmpty()) { kosong("alat masak"); return; }
        List<String> headers = Arrays.asList("Brand", "Kondisi", "Bahan", "Berat", "Jenis");
        List<List<String>> rows = new ArrayList<>();
        for (AlatMasak a : daftar) {
            rows.add(Arrays.asList(a.getNamaBrand(), a.getKondisi(), a.getBahan(),
                    desimal(a.getBerat()) + " kg", a.getJenis()));
        }
        out.print("Daftar Alat Masak:" + LN);
        printDynamicTable(headers, rows);
        out.print("Total alat masak: " + daftar.size() + LN);
    }

    static void showAlatPenyaji(Warkop w) {
        List<AlatPenyaji> daftar = w.getDapur().getDaftarAlatPenyaji();
        if (daftar.isEmpty()) { kosong("alat penyaji"); return; }
        List<String> headers = Arrays.asList("Brand", "Kondisi", "Bahan", "Jenis", "Jumlah", "Ukuran");
        List<List<String>> rows = new ArrayList<>();
        for (AlatPenyaji a : daftar) {
            rows.add(Arrays.asList(a.getNamaBrand(), a.getKondisi(), a.getBahan(),
                    a.getJenis(), Integer.toString(a.getJumlah()), a.getUkuran()));
        }
        out.print("Daftar Alat Penyaji:" + LN);
        printDynamicTable(headers, rows);
        out.print("Total alat penyaji: " + daftar.size() + LN);
    }

    // prosedur untuk menampilkan data; tanpa argumen = tampilkan semua
    static void show(Warkop w, PembacaPerintah iss) {
        String target = iss.kata();
        if (iss.gagal()) {
            showProfil(w);       out.print(LN);
            showPegawai(w);      out.print(LN);
            showMenu(w);         out.print(LN);
            showPembeli(w);      out.print(LN);
            showDistributor(w);  out.print(LN);
            showStok(w);         out.print(LN);
            showAlatMasak(w);    out.print(LN);
            showAlatPenyaji(w);
            return;
        }
        target = kecil(target);
        if (target.equals("profil")) showProfil(w);
        else if (target.equals("pegawai")) showPegawai(w);
        else if (target.equals("menu")) showMenu(w);
        else if (target.equals("pembeli")) showPembeli(w);
        else if (target.equals("distributor")) showDistributor(w);
        else if (target.equals("stok")) showStok(w);
        else if (target.equals("alatmasak")) showAlatMasak(w);
        else if (target.equals("alatpenyaji")) showAlatPenyaji(w);
        else out.print(RED + "Data \"" + target + "\" tidak dikenali. Ketik 'panduan' untuk melihat daftar." + RESET + LN);
    }

    public static void main(String[] args) throws IOException {
        out = new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), false, "ISO-8859-1");
        InputStream in = new BufferedInputStream(System.in);

        out.print(BLUE + "Selamat datang di Warkop kami!" + RESET + LN);
        out.print("(Ketik 'panduan' untuk menampilkan daftar perintah.)" + LN);

        Warkop w = new Warkop("Warkop UMR", "00:00", "23:59", "Jl. Gegerkalong No. 67, Bandung", 10.0, 7);

        // data hardcode
        w.tambahPegawai(new Pegawai("Budi", 25, "L", "Kasir",  2500000, "Pagi"));
        w.tambahPegawai(new Pegawai("Siti", 22, "P", "Barista", 2700000, "Siang"));
        w.tambahPegawai(new Pegawai("Andi", 30, "L", "Koki",   3000000, "Malam"));

        w.tambahPembeli(new Pembeli("Rina", 20, "P", "Kopi mau lebih creamy", "Instagram"));
        w.tambahPembeli(new Pembeli("Joko", 35, "L", "Makanan lebih pedas",   "Spanduk"));
        w.tambahPembeli(new Pembeli("Dewi", 28, "P", "Less ice",              "TikTok"));

        // stok dibuat di luar, lalu didaftarkan ke distributor yang memasoknya
        Distributor d1 = new Distributor("Pak Hasan", 45, "L", 1, "PT Kopi Nusantara", "Kopi");
        d1.tambahStok(new Stok("Kopi Robusta", 20, "kg"));
        d1.tambahStok(new Stok("Gula Pasir",   15, "kg"));
        w.tambahDistributor(d1);

        Distributor d2 = new Distributor("Bu Sari", 38, "P", 2, "CV Susu Segar", "Susu");
        d2.tambahStok(new Stok("Susu Kental Manis", 30, "kaleng"));
        d2.tambahStok(new Stok("Susu UHT",          24, "liter"));
        w.tambahDistributor(d2);

        w.getDapur().tambahAlatMasak(new AlatMasak("Maspion", "baru", "aluminium", 1.2, "Panci"));
        w.getDapur().tambahAlatMasak(new AlatMasak("Cosmos",  "bekas", "besi",     2.5, "Wajan"));
        w.getDapur().tambahAlatMasak(new AlatMasak("Philips", "baru", "plastik",   1.8, "Rice Cooker"));

        w.getDapur().tambahAlatPenyaji(new AlatPenyaji("Lion Star",  "baru",  "plastik", "Piring", 24, "Sedang"));
        w.getDapur().tambahAlatPenyaji(new AlatPenyaji("Duralex",    "bekas", "kaca",    "Gelas",  30, "Kecil"));
        w.getDapur().tambahAlatPenyaji(new AlatPenyaji("Tupperware", "baru",  "plastik", "Nampan", 6,  "Besar"));

        w.tambahMenu(new Menu(1, "Nasi Goreng", "Gurih", "Telur",  "Makanan", "Panas",  true,  15000));
        w.tambahMenu(new Menu(2, "Kopi Susu",   "Manis", "Susu",   "Minuman", "Dingin", true,  12000));
        w.tambahMenu(new Menu(3, "Mie Rebus",   "Pedas", "Gluten", "Makanan", "Panas",  false, 10000));
        w.tambahMenu(new Menu(4, "Es Teh",      "Manis", "-",      "Minuman", "Dingin", false, 5000));

        out.print(BLUE + "Apa yang ingin anda lakukan hari ini?" + RESET + LN);

        boolean masih = true;

        while (masih) {
            out.print("|| ");
            out.flush(); // prompt harus sudah tampil sebelum menunggu input
            String commandLine = bacaBaris(in);
            if (commandLine == null) break;
            PembacaPerintah iss = new PembacaPerintah(commandLine);
            String input = iss.kata();
            if (iss.gagal()) continue;
            input = kecil(input);

            // kalo done langsung berhenti (exit dari program)
            if (input.equals("done")) {
                masih = false;
            } else {
                if (input.equals("add")) {
                    add(w, iss);
                } else if (input.equals("show")) {
                    show(w, iss);
                } else if (input.equals("panduan")) {
                    panduan();
                } else {
                    out.print(RED + "Perintah tidak dikenali. Ketik 'panduan' untuk melihat daftar perintah." + RESET + LN);
                }
                out.print(BLUE + "Apakah ada yang ingin anda lakukan lagi?" + RESET + LN);
            }
        }

        out.print(GREEN + "Terimakasih dan silahkan datang kembali!" + RESET + LN);
        out.flush();
    }
}
