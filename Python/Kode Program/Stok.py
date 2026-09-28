class Stok:
    def __init__(self, namaBahan="", jumlah=0, satuan=""):
        self._namaBahan = namaBahan
        self._jumlah = jumlah
        self._satuan = satuan

    # getter and setter namaBahan
    def setNamaBahan(self, namaBahan):
        self._namaBahan = namaBahan

    def getNamaBahan(self):
        return self._namaBahan

    # getter and setter jumlah
    def setJumlah(self, jumlah):
        self._jumlah = jumlah

    def getJumlah(self):
        return self._jumlah

    # getter and setter satuan
    def setSatuan(self, satuan):
        self._satuan = satuan

    def getSatuan(self):
        return self._satuan
