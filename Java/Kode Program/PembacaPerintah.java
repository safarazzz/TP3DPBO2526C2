// kelas ini dibuat untuk membaca perintah (untuk support masukkan)
public class PembacaPerintah {
    private final String teks;
    private int pos = 0;
    private boolean gagal = false;

    public PembacaPerintah(String teks) {
        this.teks = teks;
    }

    public boolean gagal() {
        return gagal;
    }

    // karakter spasi menurut isspace() di C++
    private static boolean spasi(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == 0x0B || c == '\f' || c == '\r';
    }

    private static boolean angka(char c) {
        return c >= '0' && c <= '9';
    }

    private void lewatiSpasi() {
        while (pos < teks.length() && spasi(teks.charAt(pos))) pos++;
    }

    public String kata() {
        if (gagal) return "";
        lewatiSpasi();
        if (pos >= teks.length()) {
            gagal = true;
            return "";
        }
        int awal = pos;
        while (pos < teks.length() && !spasi(teks.charAt(pos))) pos++;
        return teks.substring(awal, pos);
    }

    public int bulat() {
        if (gagal) return 0;
        lewatiSpasi();
        int awal = pos;
        if (pos < teks.length() && (teks.charAt(pos) == '+' || teks.charAt(pos) == '-')) pos++;
        int awalDigit = pos;
        while (pos < teks.length() && angka(teks.charAt(pos))) pos++;
        if (pos == awalDigit) {
            gagal = true;
            return 0;
        }
        try {
            return Integer.parseInt(teks.substring(awal, pos));
        } catch (NumberFormatException e) { // di luar jangkauan int
            gagal = true;
            return 0;
        }
    }

    public double pecahan() {
        if (gagal) return 0.0;
        lewatiSpasi();
        int awal = pos;
        if (pos < teks.length() && (teks.charAt(pos) == '+' || teks.charAt(pos) == '-')) pos++;
        int jumlahDigit = 0;
        while (pos < teks.length() && angka(teks.charAt(pos))) { pos++; jumlahDigit++; }
        if (pos < teks.length() && teks.charAt(pos) == '.') {
            pos++;
            while (pos < teks.length() && angka(teks.charAt(pos))) { pos++; jumlahDigit++; }
        }
        if (jumlahDigit > 0 && pos < teks.length() && (teks.charAt(pos) == 'e' || teks.charAt(pos) == 'E')) {
            pos++;
            if (pos < teks.length() && (teks.charAt(pos) == '+' || teks.charAt(pos) == '-')) pos++;
            while (pos < teks.length() && angka(teks.charAt(pos))) pos++;
        }
        String potongan = teks.substring(awal, pos);
        if (!potongan.matches("[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?")) {
            gagal = true;
            return 0.0;
        }
        double hasil = Double.parseDouble(potongan);
        if (Double.isInfinite(hasil)) { // terlalu besar untuk double
            gagal = true;
            return 0.0;
        }
        return hasil;
    }
}
