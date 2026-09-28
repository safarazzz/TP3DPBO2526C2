# Peralatan diimpor langsung (pengganti include header di C++), dipakai
# bersama oleh AlatMasak dan AlatPenyaji lewat Dapur
from Peralatan import Peralatan


class AlatPenyaji(Peralatan):
    def __init__(self, namaBrand="", kondisi="", bahan="",
                 jenis="", jumlah=0, ukuran=""):
        # memanggil constructor Peralatan
        super().__init__(namaBrand, kondisi, bahan)
        self._jenis = jenis
        self._jumlah = jumlah
        self._ukuran = ukuran

    # getter and setter jenis
    def setJenis(self, jenis):
        self._jenis = jenis

    def getJenis(self):
        return self._jenis

    # getter and setter jumlah
    def setJumlah(self, jumlah):
        self._jumlah = jumlah

    def getJumlah(self):
        return self._jumlah

    # getter and setter ukuran
    def setUkuran(self, ukuran):
        self._ukuran = ukuran

    def getUkuran(self):
        return self._ukuran
