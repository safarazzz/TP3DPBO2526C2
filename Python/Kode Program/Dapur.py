from AlatMasak import AlatMasak
from AlatPenyaji import AlatPenyaji


class Dapur:
    def __init__(self, luasDapur=0.0, kapasitasMemasak=0):
        self._luasDapur = luasDapur
        self._kapasitasMemasak = kapasitasMemasak
        self._daftarAlatMasak = []      # agregasi
        self._daftarAlatPenyaji = []    # agregasi

    # getter and setter luasDapur
    def setLuasDapur(self, luasDapur):
        self._luasDapur = luasDapur

    def getLuasDapur(self):
        return self._luasDapur

    # getter and setter kapasitasMemasak
    def setKapasitasMemasak(self, kapasitasMemasak):
        self._kapasitasMemasak = kapasitasMemasak

    def getKapasitasMemasak(self):
        return self._kapasitasMemasak

    # getter daftar alat dan penambahan alat (dibuat di luar, lalu didaftarkan)
    def getDaftarAlatMasak(self):
        return self._daftarAlatMasak

    def getDaftarAlatPenyaji(self):
        return self._daftarAlatPenyaji

    def tambahAlatMasak(self, alat):
        self._daftarAlatMasak.append(alat)

    def tambahAlatPenyaji(self, alat):
        self._daftarAlatPenyaji.append(alat)

    # destructor tidak diperlukan; python otomatis membersihkan
    # daftarAlatMasak dan daftarAlatPenyaji lewat garbage collector
