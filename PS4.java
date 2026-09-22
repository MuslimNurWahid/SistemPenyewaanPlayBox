package penyewaanplaybox;

public class PS4 extends PlayBox {
    
    private int jumlahStik;

    public PS4(String idPlayBox, String nama,
               double hargaSewaPerJam, int jumlahStik) {

        super(idPlayBox, nama, hargaSewaPerJam);
        this.jumlahStik = jumlahStik;
    }

    public int getJumlahStik() {
        return jumlahStik;
    }
}
