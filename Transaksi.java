package penyewaanplaybox;

public class Transaksi {
    
    private String idTransaksi;
    private Pelanggan pelanggan;
    private PlayBox playBox;
    private int lamaSewa;
    private double totalHarga;

    public Transaksi(String idTransaksi, Pelanggan pelanggan,
                     PlayBox playBox, int lamaSewa) {

        this.idTransaksi = idTransaksi;
        this.pelanggan = pelanggan;
        this.playBox = playBox;
        this.lamaSewa = lamaSewa;
        this.totalHarga = playBox.hitungBiaya(lamaSewa);

        playBox.setTersedia(false);
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public PlayBox getPlayBox() {
        return playBox;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void kembalikanPlayBox() {
        playBox.setTersedia(true);
    }
}
