# Konsumsi diimpor langsung, dipakai bersama oleh Makanan dan Minuman lewat Menu
from Konsumsi import Konsumsi


class Makanan(Konsumsi):
    def __init__(self, nama="", rasa="", alergen="", kategori=""):
        # memanggil constructor Konsumsi
        super().__init__(nama, rasa, alergen)
        self._kategori = kategori

    # getter and setter kategori
    def setKategori(self, kategori):
        self._kategori = kategori

    def getKategori(self):
        return self._kategori
