package com.SistemTol;

public abstract class Kendaraan {
    protected String nomorPolisi;

    public Kendaraan(String nomorPolisi) {
        this.nomorPolisi = nomorPolisi;
    }

    public String getNomorPolisi() { return nomorPolisi; }
    public abstract String getJenis();
    public abstract String getGolongan();
    public abstract double getTarif();
}
