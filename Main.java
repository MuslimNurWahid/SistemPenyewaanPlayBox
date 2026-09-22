package penyewaanplaybox;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    static Scanner input = new Scanner(System.in);

    static ArrayList<PlayBox> daftarPlayBox = new ArrayList<>();
    static ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();
    static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
    
    public static void main(String[] args) {
        
        dataAwal();

        int pilihan;

        do {
            tampilkanMenu();
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:
                    tampilkanPlayBox();
                    break;

                case 2:
                    tambahPelanggan();
                    break;

                case 3:
                    tampilkanPelanggan();
                    break;

                case 4:
                    sewaPlayBox();
                    break;

                case 5:
                    pengembalianPlayBox();
                    break;

                case 6:
                    tampilkanTransaksi();
                    break;

                case 7:
                    pembayaran();
                    break;

                case 8:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 8);
    }

    static void dataAwal() {

        daftarPlayBox.add(
                new PS4("PB001", "PlayStation 4", 5000, 2)
        );

        daftarPlayBox.add(
                new PS4("PB002", "PlayStation 4", 5000, 2)
        );

        daftarPlayBox.add(
                new PS5("PB003", "PlayStation 5", 10000, 2)
        );

        daftarPlayBox.add(
                new PS5("PB004", "PlayStation 5", 10000, 2)
        );
    }

    static void tampilkanMenu() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       SISTEM PENYEWAAN PLAYBOX");
        System.out.println("========================================");
        System.out.println("1. Lihat Data PlayBox");
        System.out.println("2. Tambah Pelanggan");
        System.out.println("3. Lihat Data Pelanggan");
        System.out.println("4. Sewa PlayBox");
        System.out.println("5. Pengembalian PlayBox");
        System.out.println("6. Lihat Transaksi");
        System.out.println("7. Pembayaran");
        System.out.println("8. Keluar");
        System.out.println("========================================");
    }

    static void tampilkanPlayBox() {

        System.out.println();
        System.out.println("========== DATA PLAYBOX ==========");

        for (PlayBox p : daftarPlayBox) {

            System.out.println(
                    p.getIdPlayBox() + " | "
                    + p.getNama() + " | Rp"
                    + p.getHargaSewaPerJam() + "/jam | "
                    + p.getStatus()
            );
        }
    }

    static void tambahPelanggan() {

        System.out.println();
        System.out.println("========== TAMBAH PELANGGAN ==========");

        System.out.print("ID Pelanggan : ");
        String id = input.nextLine();

        System.out.print("Nama         : ");
        String nama = input.nextLine();

        System.out.print("No. Telepon  : ");
        String telepon = input.nextLine();

        Pelanggan pelanggan =
                new Pelanggan(id, nama, telepon);

        daftarPelanggan.add(pelanggan);

        System.out.println("Pelanggan berhasil ditambahkan.");
    }

    static void tampilkanPelanggan() {

        System.out.println();
        System.out.println("========== DATA PELANGGAN ==========");

        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
            return;
        }

        for (Pelanggan p : daftarPelanggan) {

            System.out.println(
                    p.getIdPelanggan() + " | "
                    + p.getNama() + " | "
                    + p.getNoTelepon()
            );
        }
    }

    static void sewaPlayBox() {

        System.out.println();
        System.out.println("========== PENYEWAAN PLAYBOX ==========");

        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada pelanggan.");
            return;
        }

        tampilkanPelanggan();

        System.out.print("Masukkan ID Pelanggan: ");
        String idPelanggan = input.nextLine();

        Pelanggan pelanggan = null;

        for (Pelanggan p : daftarPelanggan) {

            if (p.getIdPelanggan().equalsIgnoreCase(idPelanggan)) {
                pelanggan = p;
                break;
            }
        }

        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        tampilkanPlayBox();

        System.out.print("Masukkan ID PlayBox: ");
        String idPlayBox = input.nextLine();

        PlayBox playBox = null;

        for (PlayBox p : daftarPlayBox) {

            if (p.getIdPlayBox().equalsIgnoreCase(idPlayBox)) {
                playBox = p;
                break;
            }
        }

        if (playBox == null) {
            System.out.println("PlayBox tidak ditemukan.");
            return;
        }

        if (!playBox.isTersedia()) {
            System.out.println("PlayBox sedang disewa.");
            return;
        }

        System.out.print("Lama sewa (jam): ");
        int lamaSewa = input.nextInt();
        input.nextLine();

        System.out.print("ID Transaksi: ");
        String idTransaksi = input.nextLine();

        Transaksi transaksi =
                new Transaksi(
                        idTransaksi,
                        pelanggan,
                        playBox,
                        lamaSewa
                );

        daftarTransaksi.add(transaksi);

        System.out.println();
        System.out.println("Penyewaan berhasil!");
        System.out.println("Total harga: Rp"
                + transaksi.getTotalHarga());
    }

    static void pengembalianPlayBox() {

        System.out.println();
        System.out.println("========== PENGEMBALIAN ==========");

        System.out.print("Masukkan ID Transaksi: ");
        String id = input.nextLine();

        for (Transaksi t : daftarTransaksi) {

            if (t.getIdTransaksi().equalsIgnoreCase(id)) {

                t.kembalikanPlayBox();

                System.out.println(
                        "PlayBox berhasil dikembalikan."
                );

                return;
            }
        }

        System.out.println("Transaksi tidak ditemukan.");
    }

    static void tampilkanTransaksi() {

        System.out.println();
        System.out.println("========== DATA TRANSAKSI ==========");

        if (daftarTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        for (Transaksi t : daftarTransaksi) {

            System.out.println(
                    t.getIdTransaksi()
                    + " | Pelanggan: "
                    + t.getPelanggan().getNama()
                    + " | PlayBox: "
                    + t.getPlayBox().getNama()
                    + " | "
                    + t.getLamaSewa()
                    + " jam | Rp"
                    + t.getTotalHarga()
            );
        }
    }

    static void pembayaran() {

        System.out.println();
        System.out.println("========== PEMBAYARAN ==========");

        System.out.print("Masukkan ID Transaksi: ");
        String id = input.nextLine();

        Transaksi transaksi = null;

        for (Transaksi t : daftarTransaksi) {

            if (t.getIdTransaksi().equalsIgnoreCase(id)) {
                transaksi = t;
                break;
            }
        }

        if (transaksi == null) {
            System.out.println("Transaksi tidak ditemukan.");
            return;
        }

        System.out.println(
                "Total pembayaran: Rp"
                + transaksi.getTotalHarga()
        );

        System.out.print("Uang pelanggan: Rp");
        double uang = input.nextDouble();
        input.nextLine();

        if (uang < transaksi.getTotalHarga()) {

            System.out.println("Uang tidak mencukupi.");

        } else {

            double kembalian =
                    uang - transaksi.getTotalHarga();

            System.out.println(
                    "Kembalian: Rp" + kembalian
            );

            System.out.println(
                    "Pembayaran berhasil!"
            );
        }
    }
    
}
