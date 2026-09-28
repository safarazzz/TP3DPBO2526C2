from Manusia import Manusia
from Pegawai import Pegawai
from Pembeli import Pembeli
from Distributor import Distributor
from Menu import Menu
from Dapur import Dapur


class Warkop:
    def __init__(self, nama="", jamBuka="", jamTutup="", alamat="",
                 luasDapur=0.0, kapasitasMemasak=0):
        self._nama = nama
        self._jamBuka = jamBuka
        self._jamTutup = jamTutup
        self._alamat = alamat
        # komposisi: dapur ikut dibuat dan ikut hancur bareng Warkop
        self._dapur = Dapur(luasDapur, kapasitasMemasak)
        self._daftarPegawai = []          # agregasi
        self._daftarMenu = []             # agregasi
        self._daftarPembeli = []          # asosiasi (pembeli yang sedang membeli di warkop)
        self._daftarDistributor = []      # asosiasi (distributor langganan warkop)

    # getter and setter nama
    def setNama(self, nama):
        self._nama = nama

    def getNama(self):
        return self._nama

    # getter and setter jamBuka
    def setJamBuka(self, jamBuka):
        self._jamBuka = jamBuka

    def getJamBuka(self):
        return self._jamBuka

    # getter and setter jamTutup
    def setJamTutup(self, jamTutup):
        self._jamTutup = jamTutup

    def getJamTutup(self):
        return self._jamTutup

    # getter and setter alamat
    def setAlamat(self, alamat):
        self._alamat = alamat

    def getAlamat(self):
        return self._alamat

    # getter dapur dan semua daftar
    def getDapur(self):
        return self._dapur

    def getDaftarPegawai(self):
        return self._daftarPegawai

    def getDaftarMenu(self):
        return self._daftarMenu

    def getDaftarPembeli(self):
        return self._daftarPembeli

    def getDaftarDistributor(self):
        return self._daftarDistributor

    # penambahan data (objek dibuat di luar, lalu didaftarkan)
    def tambahPegawai(self, pegawai):
        self._daftarPegawai.append(pegawai)

    def tambahMenu(self, menu):
        self._daftarMenu.append(menu)

    def tambahPembeli(self, pembeli):
        self._daftarPembeli.append(pembeli)

    def tambahDistributor(self, distributor):
        self._daftarDistributor.append(distributor)

    # destructor tidak diperlukan; python otomatis membersihkan
    # semua daftar (dan dapur ikut hancur bareng Warkop) lewat garbage collector
