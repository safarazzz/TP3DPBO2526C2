# Konsumsi diimpor langsung, dipakai bersama oleh Makanan dan Minuman lewat Menu
from Konsumsi import Konsumsi


class Minuman(Konsumsi):
    def __init__(self, nama="", rasa="", alergen="", suhuPenyajian=""):
        # memanggil constructor Konsumsi
        super().__init__(nama, rasa, alergen)
        self._suhuPenyajian = suhuPenyajian

    # getter and setter suhuPenyajian
    def setSuhuPenyajian(self, suhuPenyajian):
        self._suhuPenyajian = suhuPenyajian

    def getSuhuPenyajian(self):
        return self._suhuPenyajian
