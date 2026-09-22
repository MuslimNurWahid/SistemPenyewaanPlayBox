package penyewaanplaybox;

public class PS5 extends PlayBox {
    
    private int jumlahStik;

    public PS5(String idPlayBox, String nama,
               double hargaSewaPerJam, int jumlahStik) {

        super(idPlayBox, nama, hargaSewaPerJam);
        this.jumlahStik = jumlahStik;
    }

    public int getJumlahStik() {
        return jumlahStik;
    }
}
