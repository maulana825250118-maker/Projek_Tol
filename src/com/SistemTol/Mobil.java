package com.SistemTol;
public class Mobil extends Kendaraan {
    public Mobil(String nomorPolisi) { super(nomorPolisi); }
    public String getJenis() { return "Mobil"; }
    public String getGolongan() { return "I"; }
    public double getTarif() { return 7500; }
}
