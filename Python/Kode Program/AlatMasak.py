# Peralatan diimpor langsung (pengganti include header di C++), dipakai
# bersama oleh AlatMasak dan AlatPenyaji lewat Dapur
from Peralatan import Peralatan


class AlatMasak(Peralatan):
    def __init__(self, namaBrand="", kondisi="", bahan="", berat=0.0, jenis=""):
        # memanggil constructor Peralatan
        super().__init__(namaBrand, kondisi, bahan)
        self._berat = berat
        self._jenis = jenis

    # getter and setter berat
    def setBerat(self, berat):
        self._berat = berat

    def getBerat(self):
        return self._berat

    # getter and setter jenis
    def setJenis(self, jenis):
        self._jenis = jenis

    def getJenis(self):
        return self._jenis
