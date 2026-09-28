class Peralatan:
    # constructor kosong & berparameter digabung pakai default value
    # (python tidak mendukung overloading constructor seperti C++)
    def __init__(self, namaBrand="", kondisi="", bahan=""):
        # atribut privat (pakai underscore, python tidak punya private sungguhan)
        self._namaBrand = namaBrand
        self._kondisi = kondisi
        self._bahan = bahan

    # getter and setter namaBrand
    def setNamaBrand(self, namaBrand):
        self._namaBrand = namaBrand

    def getNamaBrand(self):
        return self._namaBrand

    # getter and setter kondisi
    def setKondisi(self, kondisi):
        self._kondisi = kondisi

    def getKondisi(self):
        return self._kondisi

    # getter and setter bahan
    def setBahan(self, bahan):
        self._bahan = bahan

    def getBahan(self):
        return self._bahan

    # destructor tidak diperlukan di python, garbage collector
    # otomatis membersihkan objek yang sudah tidak dipakai
