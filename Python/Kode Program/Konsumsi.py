class Konsumsi:
    def __init__(self, nama="", rasa="", alergen=""):
        self._nama = nama
        self._rasa = rasa
        self._alergen = alergen

    # getter and setter nama
    def setNama(self, nama):
        self._nama = nama

    def getNama(self):
        return self._nama

    # getter and setter rasa
    def setRasa(self, rasa):
        self._rasa = rasa

    def getRasa(self):
        return self._rasa

    # getter and setter alergen
    def setAlergen(self, alergen):
        self._alergen = alergen

    def getAlergen(self):
        return self._alergen
