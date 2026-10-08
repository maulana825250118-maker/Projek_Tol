package com.SistemTol;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SwingUtilities;

public class Transaksi implements Cetak {
    private static int nomorTransaksi = 1;
    private String idTransaksi;
    private Kendaraan kendaraan;
    private GerbangTol gerbangAwal, gerbangTujuan;
    private KartuElektronik kartu;
    private Gate gate;
    private double saldoAwal, tarif, saldoAkhir;
    private boolean berhasil;
    private String tanggal;

    public Transaksi(Kendaraan kendaraan, GerbangTol gerbangAwal,
                     GerbangTol gerbangTujuan, KartuElektronik kartu) {
        this.idTransaksi = String.format("TRX%03d", ambilNomorTransaksiBerikutnya());
        this.kendaraan = kendaraan;
        this.gerbangAwal = gerbangAwal;
        this.gerbangTujuan = gerbangTujuan;
        this.kartu = kartu;
        this.gate = new Gate();
        this.saldoAwal = kartu.getSaldo();
        this.tanggal = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    private static int ambilNomorTransaksiBerikutnya() {
        int nomorBerikutnya = 1;
        File file = new File("data/transaksi.txt");

        if (file.exists()) {
            Pattern pola = Pattern.compile("(?:No\\. Transaksi\\s*:\\s*)?TRX(\\d+)");

            try {
                String isi = new String(Files.readAllBytes(Paths.get("data/transaksi.txt")));
                Matcher matcher = pola.matcher(isi);

                while (matcher.find()) {
                    int nomor = Integer.parseInt(matcher.group(1));
                    if (nomor >= nomorBerikutnya) {
                        nomorBerikutnya = nomor + 1;
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Gagal membaca nomor transaksi sebelumnya.");
            }
        }

        nomorTransaksi = nomorBerikutnya + 1;
        return nomorBerikutnya;
    }

    public void proses() {
        tarif = kendaraan.getTarif();

        if (kartu.bayar(tarif)) {
            saldoAkhir = kartu.getSaldo();
            berhasil = true;
            gate.buka();
        } else {
            saldoAkhir = saldoAwal;
            berhasil = false;
            System.out.println("\nSaldo tidak mencukupi. Transaksi gagal.");
        }
    }

    @Override
    public void cetak() {
        // Struk hanya ditampilkan jika transaksi berhasil.
        if (!berhasil) {
            return;
        }

        String status = "TRANSAKSI BERHASIL";
        String statusGate = gate.getStatus();

        SwingUtilities.invokeLater(() -> {
            StrukFrame frame = new StrukFrame(
                idTransaksi,
                tanggal,
                gerbangAwal.getKode() + " - " + gerbangAwal.getNama(),
                gerbangTujuan.getKode() + " - " + gerbangTujuan.getNama(),
                kendaraan.getJenis(),
                kendaraan.getGolongan(),
                kendaraan.getNomorPolisi(),
                kartu.getNomorKartu(),
                formatRupiah(tarif),
                formatRupiah(saldoAwal),
                formatRupiah(saldoAkhir),
                status,
                statusGate
            );
            frame.setVisible(true);
        });
    }

    public void simpanKeFile() {
        // Transaksi gagal tidak disimpan ke file.
        if (!berhasil) {
            return;
        }

        try {
            File folder = new File("data");
            if (!folder.exists()) folder.mkdir();

            FileWriter writer = new FileWriter("data/transaksi.txt", true);
            writer.write(idTransaksi + "|" + tanggal + "|"
                + kendaraan.getJenis() + "|" + kendaraan.getGolongan() + "|"
                + kendaraan.getNomorPolisi() + "|"
                + gerbangAwal.getKode() + "|" + gerbangAwal.getNama() + "|"
                + gerbangTujuan.getKode() + "|" + gerbangTujuan.getNama() + "|"
                + kartu.getNomorKartu() + "|"
                + formatRupiah(tarif) + "|"
                + formatRupiah(saldoAwal) + "|"
                + formatRupiah(saldoAkhir) + "|TRANSAKSI BERHASIL\n");
            writer.close();
            System.out.println("\nData transaksi berhasil disimpan.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan transaksi: " + e.getMessage());
        }
    }

    private String formatRupiah(double angka) {
        return String.format("%,.0f", angka).replace(",", ".");
    }
}
