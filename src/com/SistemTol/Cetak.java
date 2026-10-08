package com.SistemTol;
import javax.swing.*;
import java.awt.*;

public interface Cetak {
    void cetak();

    
    class StrukFrame extends JFrame {
        public StrukFrame(String idTransaksi, String tanggal,
                          String gerbangAwal, String gerbangTujuan,
                          String kendaraan, String golongan, String nomorPolisi,
                          String nomorKartu, String tarif, String saldoAwal,
                          String saldoAkhir, String status, String statusGate) {

            setTitle("Struk Tol Jagorawi");
            setSize(430, 560);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JTextArea struk = new JTextArea();
            struk.setFont(new Font("Monospaced", Font.PLAIN, 13));
            struk.setEditable(false);
            struk.setMargin(new Insets(15, 15, 15, 15));

            StringBuilder isi = new StringBuilder();
            isi.append("========================================\n");
            isi.append("          STRUK TOL JAGORAWI\n");
            isi.append("========================================\n");
            isi.append(String.format("No. Transaksi : %s%n", idTransaksi));
            isi.append(String.format("Tanggal       : %s%n", tanggal));
            isi.append("----------------------------------------\n");
            isi.append(String.format("Gerbang Awal  : %s%n", gerbangAwal));
            isi.append(String.format("Gerbang Tujuan: %s%n", gerbangTujuan));
            isi.append("----------------------------------------\n");
            isi.append(String.format("Kendaraan     : %s%n", kendaraan));
            isi.append(String.format("Golongan      : %s%n", golongan));
            isi.append(String.format("No. Polisi    : %s%n", nomorPolisi));
            isi.append(String.format("Nomor Kartu   : %s%n", nomorKartu));
            isi.append("----------------------------------------\n");
            isi.append(String.format("Tarif         : Rp%s%n", tarif));
            isi.append(String.format("Saldo Awal    : Rp%s%n", saldoAwal));
            isi.append(String.format("Saldo Akhir   : Rp%s%n", saldoAkhir));
            isi.append("----------------------------------------\n");
            isi.append(String.format("Status        : %s%n", status));
            isi.append("========================================\n");

            struk.setText(isi.toString());
            add(new JScrollPane(struk));
        }
    }
}
