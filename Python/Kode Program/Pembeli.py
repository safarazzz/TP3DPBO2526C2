# Manusia diimpor langsung, dipakai bersama oleh Pegawai, Pembeli, Distributor
from Manusia import Manusia


class Pembeli(Manusia):
    def __init__(self, nama="", umur=0, jenisKelamin="",
                 referensi="", sumberInfo=""):
        # memanggil constructor Manusia
        super().__init__(nama, umur, jenisKelamin)
        self._referensi = referensi
        self._sumberInfo = sumberInfo

    # getter and setter referensi
    def setReferensi(self, referensi):
        self._referensi = referensi

    def getReferensi(self):
        return self._referensi

    # getter and setter sumberInfo
    def setSumberInfo(self, sumberInfo):
        self._sumberInfo = sumberInfo

    def getSumberInfo(self):
        return self._sumberInfo
