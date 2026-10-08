package com.SistemTol;
public class Truk extends Kendaraan {
    public Truk(String nomorPolisi) { super(nomorPolisi); }
    public String getJenis() { return "Truk"; }
    public String getGolongan() { return "III"; }
    public double getTarif() { return 15000; }
}
