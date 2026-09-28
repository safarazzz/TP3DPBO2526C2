from Warkop import Warkop
from Pegawai import Pegawai
from Pembeli import Pembeli
from Distributor import Distributor
from Stok import Stok
from AlatMasak import AlatMasak
from AlatPenyaji import AlatPenyaji
from Menu import Menu

# deklarasi penyingkat dan warna
RED = "\033[31m"
BLUE = "\033[34m"
GREEN = "\033[32m"
RESET = "\033[0m"


# helper untuk mencetak garis pembatas tabel sesuai lebar tiap kolom
def printLine(widths):
    line = "+"
    for w in widths:
        line += "-" * (w + 2) + "+"
    print(line)


# helper untuk mencetak satu baris tabel sesuai lebar tiap kolom
def printRow(cells, widths):
    row = "|"
    for cell, w in zip(cells, widths):
        row += " " + cell.ljust(w) + " |"
    print(row)


# mencetak tabel DINAMIS: lebar tiap kolom dihitung otomatis dari
# panjang data terpanjang di kolom tersebut (termasuk headernya),
# jadi tabel selalu rapi berapapun panjang data yang dimasukkan.
def printDynamicTable(headers, rows):
    jumlahKolom = len(headers)
    width = [len(h) for h in headers]

    for row in rows:
        for c in range(jumlahKolom):
            if len(row[c]) > width[c]:
                width[c] = len(row[c])

    printLine(width)
    printRow(headers, width)
    printLine(width)
    for row in rows:
        printRow(row, width)
    printLine(width)


# mengubah angka desimal menjadi string dengan satu angka di belakang koma
def desimal(x):
    return f"{x:.1f}"


# mengubah tanda _ menjadi spasi supaya input satu kata bisa berisi spasi (contoh: Nasi_Goreng)
def rapikan(s):
    return s.replace("_", " ")


# mengubah string menjadi huruf kecil semua
def kecil(s):
    return s.lower()


# prosedur untuk menampilkan pesan kesalahan ketika suatu daftar masih kosong
def kosong(namaDaftar):
    print(f"{RED}Daftar {namaDaftar} masih kosong!{RESET}")


# prosedur untuk menampilkan pesan kesalahan format perintah
def formatSalah(fmt):
    print(f"{RED}Format salah. Gunakan: {fmt}{RESET}")


# mencari distributor berdasarkan id, mengembalikan None kalau tidak ditemukan
def cariDistributor(w, id_):
    for d in w.getDaftarDistributor():
        if d.getIdDistributor() == id_:
            return d
    return None


# mencari menu berdasarkan id, mengembalikan None kalau tidak ditemukan
def cariMenu(w, id_):
    for m in w.getDaftarMenu():
        if m.getIdMenu() == id_:
            return m
    return None


# prosedur panduan untuk menampilkan daftar perintah yang tersedia
def panduan():
    headers = ["Perintah", "Format Penggunaan", "Keterangan"]
    rows = [
        ["add pegawai", "add pegawai (nama) (umur) (kelamin) (pekerjaan) (gaji) (shift)", "Menambahkan satu pegawai."],
        ["add pembeli", "add pembeli (nama) (umur) (kelamin) (referensi) (sumberInfo)", "Menambahkan satu pembeli."],
        ["add distributor", "add distributor (id) (nama) (umur) (kelamin) (perusahaan) (jenisBarang)", "Menambahkan satu distributor (id tidak boleh kembar)."],
        ["add stok", "add stok (idDistributor) (namaBahan) (jumlah) (satuan)", "Menambahkan stok ke distributor sesuai id di tabel distributor."],
        ["add alatmasak", "add alatmasak (brand) (kondisi) (bahan) (berat) (jenis)", "Menambahkan satu alat masak ke dapur."],
        ["add alatpenyaji", "add alatpenyaji (brand) (kondisi) (bahan) (jenis) (jumlah) (ukuran)", "Menambahkan satu alat penyaji ke dapur."],
        ["add menu", "add menu (id) (nama) (rasa) (alergen) (kategori) (suhu) (rekomen y/n) (harga)", "Menambahkan satu menu makanan/minuman (id tidak boleh kembar)."],
        ["show", "show (profil/pegawai/menu/pembeli/distributor/stok/alatmasak/alatpenyaji)", "Menampilkan data dalam tabel dinamis. Tanpa tambahan = tampilkan semua."],
        ["panduan", "panduan", "Menampilkan panduan ini."],
        ["done", "done", "Mengakhiri sesi program."],
    ]
    print("Panduan penggunaan program Warkop")
    printDynamicTable(headers, rows)
    print("Catatan: pakai tanda _ sebagai pengganti spasi (contoh: Nasi_Goreng).")
    print()


# ================= prosedur add =================

def addPegawai(w, tokens):
    if len(tokens) < 6:
        formatSalah("add pegawai (nama) (umur) (kelamin) (pekerjaan) (gaji) (shift)")
        return
    nama, umurStr, kelamin, pekerjaan, gajiStr, shift = tokens[:6]
    try:
        umur = int(umurStr)
        gaji = float(gajiStr)
    except ValueError:
        formatSalah("add pegawai (nama) (umur) (kelamin) (pekerjaan) (gaji) (shift)")
        return
    nama = rapikan(nama)
    w.tambahPegawai(Pegawai(nama, umur, kelamin, rapikan(pekerjaan), gaji, rapikan(shift)))
    print(f'{GREEN}Pegawai "{nama}" berhasil ditambahkan.{RESET}')


def addPembeli(w, tokens):
    if len(tokens) < 5:
        formatSalah("add pembeli (nama) (umur) (kelamin) (referensi) (sumberInfo)")
        return
    nama, umurStr, kelamin, referensi, sumberInfo = tokens[:5]
    try:
        umur = int(umurStr)
    except ValueError:
        formatSalah("add pembeli (nama) (umur) (kelamin) (referensi) (sumberInfo)")
        return
    nama = rapikan(nama)
    w.tambahPembeli(Pembeli(nama, umur, kelamin, rapikan(referensi), rapikan(sumberInfo)))
    print(f'{GREEN}Pembeli "{nama}" berhasil ditambahkan.{RESET}')


def addDistributor(w, tokens):
    if len(tokens) < 6:
        formatSalah("add distributor (id) (nama) (umur) (kelamin) (perusahaan) (jenisBarang)")
        return
    idStr, nama, umurStr, kelamin, perusahaan, jenisBarang = tokens[:6]
    try:
        id_ = int(idStr)
        umur = int(umurStr)
    except ValueError:
        formatSalah("add distributor (id) (nama) (umur) (kelamin) (perusahaan) (jenisBarang)")
        return
    ada = cariDistributor(w, id_)
    if ada is not None:
        print(f'{RED}Id distributor {id_} sudah dipakai oleh {ada.getNama()}. Gunakan id lain.{RESET}')
        return
    nama = rapikan(nama)
    w.tambahDistributor(Distributor(nama, umur, kelamin, id_, rapikan(perusahaan), rapikan(jenisBarang)))
    print(f'{GREEN}Distributor "{nama}" berhasil ditambahkan dengan id {id_}.{RESET}')


def addStok(w, tokens):
    if len(tokens) < 4:
        formatSalah("add stok (idDistributor) (namaBahan) (jumlah) (satuan)")
        return
    idStr, namaBahan, jumlahStr, satuan = tokens[:4]
    try:
        id_ = int(idStr)
        jumlah = int(jumlahStr)
    except ValueError:
        formatSalah("add stok (idDistributor) (namaBahan) (jumlah) (satuan)")
        return
    if not w.getDaftarDistributor():
        print(f"{RED}Belum ada distributor. Tambahkan distributor dulu dengan 'add distributor'.{RESET}")
        return
    d = cariDistributor(w, id_)
    if d is None:
        print(f"{RED}Distributor dengan id {id_} tidak ditemukan (lihat id di 'show distributor').{RESET}")
        return
    namaBahan = rapikan(namaBahan)
    d.tambahStok(Stok(namaBahan, jumlah, rapikan(satuan)))
    print(f'{GREEN}Stok "{namaBahan}" berhasil ditambahkan ke {d.getPerusahaan()}.{RESET}')


def addAlatMasak(w, tokens):
    if len(tokens) < 5:
        formatSalah("add alatmasak (brand) (kondisi) (bahan) (berat) (jenis)")
        return
    brand, kondisi, bahan, beratStr, jenis = tokens[:5]
    try:
        berat = float(beratStr)
    except ValueError:
        formatSalah("add alatmasak (brand) (kondisi) (bahan) (berat) (jenis)")
        return
    brand = rapikan(brand)
    w.getDapur().tambahAlatMasak(AlatMasak(brand, rapikan(kondisi), rapikan(bahan), berat, rapikan(jenis)))
    print(f'{GREEN}Alat masak "{brand}" berhasil ditambahkan.{RESET}')


def addAlatPenyaji(w, tokens):
    if len(tokens) < 6:
        formatSalah("add alatpenyaji (brand) (kondisi) (bahan) (jenis) (jumlah) (ukuran)")
        return
    brand, kondisi, bahan, jenis, jumlahStr, ukuran = tokens[:6]
    try:
        jumlah = int(jumlahStr)
    except ValueError:
        formatSalah("add alatpenyaji (brand) (kondisi) (bahan) (jenis) (jumlah) (ukuran)")
        return
    brand = rapikan(brand)
    w.getDapur().tambahAlatPenyaji(
        AlatPenyaji(brand, rapikan(kondisi), rapikan(bahan), rapikan(jenis), jumlah, rapikan(ukuran))
    )
    print(f'{GREEN}Alat penyaji "{brand}" berhasil ditambahkan.{RESET}')


def addMenu(w, tokens):
    if len(tokens) < 8:
        formatSalah("add menu (id) (nama) (rasa) (alergen) (kategori) (suhu) (rekomen y/n) (harga)")
        return
    idStr, nama, rasa, alergen, kategori, suhu, rekomenStr, hargaStr = tokens[:8]
    try:
        id_ = int(idStr)
        harga = float(hargaStr)
    except ValueError:
        formatSalah("add menu (id) (nama) (rasa) (alergen) (kategori) (suhu) (rekomen y/n) (harga)")
        return
    ada = cariMenu(w, id_)
    if ada is not None:
        print(f'{RED}Id menu {id_} sudah dipakai oleh {ada.getNama()}. Gunakan id lain.{RESET}')
        return
    nama = rapikan(nama)
    rek = kecil(rekomenStr) == "y"
    w.tambahMenu(Menu(id_, nama, rapikan(rasa), rapikan(alergen), rapikan(kategori), rapikan(suhu), rek, harga))
    print(f'{GREEN}Menu "{nama}" berhasil ditambahkan dengan id {id_}.{RESET}')


# prosedur untuk menambahkan data baru berdasarkan jenis yang diketik setelah 'add'
def add(w, tokens):
    if not tokens:
        print(f"{RED}Format salah. Ketik 'panduan' untuk melihat format perintah add.{RESET}")
        return
    jenis = kecil(tokens[0])
    sisa = tokens[1:]
    if jenis == "pegawai":
        addPegawai(w, sisa)
    elif jenis == "pembeli":
        addPembeli(w, sisa)
    elif jenis == "distributor":
        addDistributor(w, sisa)
    elif jenis == "stok":
        addStok(w, sisa)
    elif jenis == "alatmasak":
        addAlatMasak(w, sisa)
    elif jenis == "alatpenyaji":
        addAlatPenyaji(w, sisa)
    elif jenis == "menu":
        addMenu(w, sisa)
    else:
        print(f'{RED}Jenis data "{jenis}" tidak dikenali. Ketik \'panduan\' untuk melihat daftar.{RESET}')


# ================= prosedur show =================

def showProfil(w):
    headers = ["Nama", "Alamat", "Jam Buka", "Jam Tutup", "Luas Dapur (m2)", "Kapasitas Masak"]
    rows = [[
        w.getNama(), w.getAlamat(), w.getJamBuka(), w.getJamTutup(),
        desimal(w.getDapur().getLuasDapur()), str(w.getDapur().getKapasitasMemasak())
    ]]
    print("Profil Warkop:")
    printDynamicTable(headers, rows)


def showPegawai(w):
    daftar = w.getDaftarPegawai()
    if not daftar:
        kosong("pegawai")
        return
    headers = ["Nama", "Umur", "Kelamin", "Pekerjaan", "Gaji", "Shift"]
    rows = []
    for p in daftar:
        rows.append([p.getNama(), str(p.getUmur()), p.getJenisKelamin(),
                     p.getJenisPekerjaan(), str(int(p.getGaji())), p.getShift()])
    print("Daftar Pegawai:")
    printDynamicTable(headers, rows)
    print(f"Total pegawai: {len(daftar)}")


def showMenu(w):
    daftar = w.getDaftarMenu()
    if not daftar:
        kosong("menu")
        return
    headers = ["ID", "Nama", "Rasa", "Alergen", "Kategori", "Suhu", "Rekomen", "Harga"]
    rows = []
    for m in daftar:
        rows.append([str(m.getIdMenu()), m.getNama(), m.getRasa(), m.getAlergen(),
                     m.getKategori(), m.getSuhuPenyajian(), "Ya" if m.getRekomen() else "Tidak",
                     str(int(m.getHarga()))])
    print("Daftar Menu:")
    printDynamicTable(headers, rows)
    print(f"Total menu: {len(daftar)}")


def showPembeli(w):
    daftar = w.getDaftarPembeli()
    if not daftar:
        kosong("pembeli")
        return
    headers = ["Nama", "Umur", "Kelamin", "Referensi", "SumberInfo"]
    rows = []
    for p in daftar:
        rows.append([p.getNama(), str(p.getUmur()), p.getJenisKelamin(),
                     p.getReferensi(), p.getSumberInfo()])
    print("Daftar Pembeli:")
    printDynamicTable(headers, rows)
    print(f"Total pembeli: {len(daftar)}")


def showDistributor(w):
    daftar = w.getDaftarDistributor()
    if not daftar:
        kosong("distributor")
        return
    headers = ["ID", "Nama", "Umur", "Kelamin", "Perusahaan", "JenisBarang"]
    rows = []
    for d in daftar:
        rows.append([str(d.getIdDistributor()), d.getNama(), str(d.getUmur()), d.getJenisKelamin(),
                     d.getPerusahaan(), d.getJenisBarang()])
    print("Daftar Distributor:")
    printDynamicTable(headers, rows)
    print(f"Total distributor: {len(daftar)}")


# stok dari semua distributor digabung dalam satu tabel
def showStok(w):
    headers = ["Distributor", "NamaBahan", "Jumlah", "Satuan"]
    rows = []
    for d in w.getDaftarDistributor():
        for s in d.getDaftarStok():
            rows.append([d.getPerusahaan(), s.getNamaBahan(), str(s.getJumlah()), s.getSatuan()])
    if not rows:
        kosong("stok bahan")
        return
    print("Daftar Stok Bahan:")
    printDynamicTable(headers, rows)
    print(f"Total jenis stok: {len(rows)}")


def showAlatMasak(w):
    daftar = w.getDapur().getDaftarAlatMasak()
    if not daftar:
        kosong("alat masak")
        return
    headers = ["Brand", "Kondisi", "Bahan", "Berat", "Jenis"]
    rows = []
    for a in daftar:
        rows.append([a.getNamaBrand(), a.getKondisi(), a.getBahan(),
                     desimal(a.getBerat()) + " kg", a.getJenis()])
    print("Daftar Alat Masak:")
    printDynamicTable(headers, rows)
    print(f"Total alat masak: {len(daftar)}")


def showAlatPenyaji(w):
    daftar = w.getDapur().getDaftarAlatPenyaji()
    if not daftar:
        kosong("alat penyaji")
        return
    headers = ["Brand", "Kondisi", "Bahan", "Jenis", "Jumlah", "Ukuran"]
    rows = []
    for a in daftar:
        rows.append([a.getNamaBrand(), a.getKondisi(), a.getBahan(),
                     a.getJenis(), str(a.getJumlah()), a.getUkuran()])
    print("Daftar Alat Penyaji:")
    printDynamicTable(headers, rows)
    print(f"Total alat penyaji: {len(daftar)}")


# prosedur untuk menampilkan data; tanpa argumen = tampilkan semua
def show(w, tokens):
    if not tokens:
        showProfil(w)
        print()
        showPegawai(w)
        print()
        showMenu(w)
        print()
        showPembeli(w)
        print()
        showDistributor(w)
        print()
        showStok(w)
        print()
        showAlatMasak(w)
        print()
        showAlatPenyaji(w)
        return
    target = kecil(tokens[0])
    if target == "profil":
        showProfil(w)
    elif target == "pegawai":
        showPegawai(w)
    elif target == "menu":
        showMenu(w)
    elif target == "pembeli":
        showPembeli(w)
    elif target == "distributor":
        showDistributor(w)
    elif target == "stok":
        showStok(w)
    elif target == "alatmasak":
        showAlatMasak(w)
    elif target == "alatpenyaji":
        showAlatPenyaji(w)
    else:
        print(f'{RED}Data "{target}" tidak dikenali. Ketik \'panduan\' untuk melihat daftar.{RESET}')


def main():
    print(f"{BLUE}Selamat datang di Warkop kami!{RESET}")
    print("(Ketik 'panduan' untuk menampilkan daftar perintah.)")

    w = Warkop("Warkop Barokah", "07:00", "22:00", "Jl. Merdeka No. 10, Bandung", 20.0, 4)

    # data hardcode
    w.tambahPegawai(Pegawai("Budi", 25, "L", "Kasir", 2500000, "Pagi"))
    w.tambahPegawai(Pegawai("Siti", 22, "P", "Barista", 2700000, "Siang"))
    w.tambahPegawai(Pegawai("Andi", 30, "L", "Koki", 3000000, "Malam"))

    w.tambahPembeli(Pembeli("Rina", 20, "P", "Kopi mau lebih creamy", "Instagram"))
    w.tambahPembeli(Pembeli("Joko", 35, "L", "Makanan lebih pedas", "Spanduk"))
    w.tambahPembeli(Pembeli("Dewi", 28, "P", "Less ice", "TikTok"))

    # stok dibuat di luar, lalu didaftarkan ke distributor yang memasoknya
    d1 = Distributor("Pak Hasan", 45, "L", 1, "PT Kopi Nusantara", "Kopi")
    d1.tambahStok(Stok("Kopi Robusta", 20, "kg"))
    d1.tambahStok(Stok("Gula Pasir", 15, "kg"))
    w.tambahDistributor(d1)

    d2 = Distributor("Bu Sari", 38, "P", 2, "CV Susu Segar", "Susu")
    d2.tambahStok(Stok("Susu Kental Manis", 30, "kaleng"))
    d2.tambahStok(Stok("Susu UHT", 24, "liter"))
    w.tambahDistributor(d2)

    w.getDapur().tambahAlatMasak(AlatMasak("Maspion", "baru", "aluminium", 1.2, "Panci"))
    w.getDapur().tambahAlatMasak(AlatMasak("Cosmos", "bekas", "besi", 2.5, "Wajan"))
    w.getDapur().tambahAlatMasak(AlatMasak("Philips", "baru", "plastik", 1.8, "Rice Cooker"))

    w.getDapur().tambahAlatPenyaji(AlatPenyaji("Lion Star", "baru", "plastik", "Piring", 24, "Sedang"))
    w.getDapur().tambahAlatPenyaji(AlatPenyaji("Duralex", "bekas", "kaca", "Gelas", 30, "Kecil"))
    w.getDapur().tambahAlatPenyaji(AlatPenyaji("Tupperware", "baru", "plastik", "Nampan", 6, "Besar"))

    w.tambahMenu(Menu(1, "Nasi Goreng", "Gurih", "Telur", "Makanan", "Panas", True, 15000))
    w.tambahMenu(Menu(2, "Kopi Susu", "Manis", "Susu", "Minuman", "Dingin", True, 12000))
    w.tambahMenu(Menu(3, "Mie Rebus", "Pedas", "Gluten", "Makanan", "Panas", False, 10000))
    w.tambahMenu(Menu(4, "Es Teh", "Manis", "-", "Minuman", "Dingin", False, 5000))

    print(f"{BLUE}Apa yang ingin anda lakukan hari ini?{RESET}")

    masih = True
    while masih:
        try:
            commandLine = input("|| ")
        except EOFError:
            break

        tokens = commandLine.split()
        if not tokens:
            continue
        cmd = kecil(tokens[0])
        sisa = tokens[1:]

        # kalo done langsung berhenti (exit dari program)
        if cmd == "done":
            masih = False
        else:
            if cmd == "add":
                add(w, sisa)
            elif cmd == "show":
                show(w, sisa)
            elif cmd == "panduan":
                panduan()
            else:
                print(f"{RED}Perintah tidak dikenali. Ketik 'panduan' untuk melihat daftar perintah.{RESET}")
            print(f"{BLUE}Apakah ada yang ingin anda lakukan lagi?{RESET}")

    print(f"{GREEN}Terimakasih dan silahkan datang kembali!{RESET}")


if __name__ == "__main__":
    main()
