# Manusia diimpor langsung, dipakai bersama oleh Pegawai, Pembeli, Distributor
from Manusia import Manusia


class Pegawai(Manusia):
    def __init__(self, nama="", umur=0, jenisKelamin="",
                 jenisPekerjaan="", gaji=0.0, shift=""):
        # memanggil constructor Manusia
        super().__init__(nama, umur, jenisKelamin)
        self._jenisPekerjaan = jenisPekerjaan
        self._gaji = gaji
        self._shift = shift

    # getter and setter jenisPekerjaan
    def setJenisPekerjaan(self, jenisPekerjaan):
        self._jenisPekerjaan = jenisPekerjaan

    def getJenisPekerjaan(self):
        return self._jenisPekerjaan

    # getter and setter gaji
    def setGaji(self, gaji):
        self._gaji = gaji

    def getGaji(self):
        return self._gaji

    # getter and setter shift
    def setShift(self, shift):
        self._shift = shift

    def getShift(self):
        return self._shift
