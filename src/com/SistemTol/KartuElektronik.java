package com.SistemTol;
public class KartuElektronik {
    private String nomorKartu;
    private double saldo;

    public KartuElektronik(String nomorKartu, double saldo) {
        this.nomorKartu = nomorKartu;
        this.saldo = saldo;
    }

    public String getNomorKartu() { return nomorKartu; }
    public double getSaldo() { return saldo; }

    public boolean bayar(double tarif) {
        if (saldo >= tarif) {
            saldo -= tarif;
            return true;
        }
        return false;
    }
}
