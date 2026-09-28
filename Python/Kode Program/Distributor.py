# Manusia diimpor langsung, dipakai bersama oleh Pegawai, Pembeli, Distributor
from Manusia import Manusia
from Stok import Stok


class Distributor(Manusia):
    def __init__(self, nama="", umur=0, jenisKelamin="",
                 idDistributor=0, perusahaan="", jenisBarang=""):
        # memanggil constructor Manusia
        super().__init__(nama, umur, jenisKelamin)
        self._idDistributor = idDistributor
        self._perusahaan = perusahaan
        self._jenisBarang = jenisBarang
        self._daftarStok = []  # asosiasi: stok bahan yang dipasok distributor ini

    # getter and setter idDistributor
    def setIdDistributor(self, idDistributor):
        self._idDistributor = idDistributor

    def getIdDistributor(self):
        return self._idDistributor

    # getter and setter perusahaan
    def setPerusahaan(self, perusahaan):
        self._perusahaan = perusahaan

    def getPerusahaan(self):
        return self._perusahaan

    # getter and setter jenisBarang
    def setJenisBarang(self, jenisBarang):
        self._jenisBarang = jenisBarang

    def getJenisBarang(self):
        return self._jenisBarang

    # getter daftar stok dan penambahan stok (dibuat di luar, lalu didaftarkan)
    def getDaftarStok(self):
        return self._daftarStok

    def tambahStok(self, stok):
        self._daftarStok.append(stok)

    # destructor tidak diperlukan; python otomatis membersihkan daftarStok
