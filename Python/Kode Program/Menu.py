from Konsumsi import Konsumsi
from Makanan import Makanan
from Minuman import Minuman


# multiple inheritance: Menu mewarisi dua class sekaligus (Makanan & Minuman).
# Di C++ dibutuhkan "virtual inheritance" di Makanan & Minuman supaya Konsumsi
# tidak punya dua salinan (diamond inheritance problem). Di Python, hal ini
# otomatis ditangani lewat MRO (Method Resolution Order), tapi supaya Konsumsi
# hanya diinisialisasi SEKALI dengan nilai yang benar, Menu tetap
# menginisialisasi Konsumsi secara langsung, persis seperti versi C++-nya.
class Menu(Makanan, Minuman):
    def __init__(self, idMenu=0, nama="", rasa="", alergen="",
                 kategori="", suhuPenyajian="", rekomen=False, harga=0.0):
        # memanggil constructor Konsumsi secara langsung (bukan lewat
        # Makanan/Minuman) supaya atribut nama/rasa/alergen hanya diset sekali
        Konsumsi.__init__(self, nama, rasa, alergen)
        self._kategori = kategori
        self._suhuPenyajian = suhuPenyajian
        self._idMenu = idMenu
        self._rekomen = rekomen
        self._harga = harga

    # getter and setter idMenu
    def setIdMenu(self, idMenu):
        self._idMenu = idMenu

    def getIdMenu(self):
        return self._idMenu

    # getter and setter rekomen
    def setRekomen(self, rekomen):
        self._rekomen = rekomen

    def getRekomen(self):
        return self._rekomen

    # getter and setter harga
    def setHarga(self, harga):
        self._harga = harga

    def getHarga(self):
        return self._harga
