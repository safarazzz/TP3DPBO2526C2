class Manusia:
    def __init__(self, nama="", umur=0, jenisKelamin=""):
        self._nama = nama
        self._umur = umur
        self._jenisKelamin = jenisKelamin

    # getter and setter nama
    def setNama(self, nama):
        self._nama = nama

    def getNama(self):
        return self._nama

    # getter and setter umur
    def setUmur(self, umur):
        self._umur = umur

    def getUmur(self):
        return self._umur

    # getter and setter jenisKelamin
    def setJenisKelamin(self, jenisKelamin):
        self._jenisKelamin = jenisKelamin

    def getJenisKelamin(self):
        return self._jenisKelamin

    # destructor tidak diperlukan di python
