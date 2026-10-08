package com.SistemTol;

public class GerbangTol {
    private String kode;
    private String nama;

    public GerbangTol(String kode) {
        this.kode = kode;
        this.nama = getNamaGerbang(kode);
    }

    private String getNamaGerbang(String kode) {
        switch (kode.toLowerCase()) {
            case "i001": return "Jakarta";
            case "i002": return "Sentul";
            case "i003": return "Bogor";
            case "i004": return "Ciawi";
            default: return "Tidak Dikenal";
        }
    }

    public boolean isValid() { return !nama.equals("Tidak Dikenal"); }
    public String getKode() { return kode; }
    public String getNama() { return nama; }
}
