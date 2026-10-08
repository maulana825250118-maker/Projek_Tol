package com.SistemTol;

public class Bus extends Kendaraan {
    public Bus(String nomorPolisi) { super(nomorPolisi); }
    public String getJenis() { return "Bus"; }
    public String getGolongan() { return "II"; }
    public double getTarif() { return 12000; }
}
