package com.SistemTol;

public class Gate {
    private String status = "Tertutup";

    public void buka() {
        status = "Terbuka";
        System.out.println("Gerbang tol terbuka.");
    }

    public void tutup() { status = "Tertutup"; }
    public String getStatus() { return status; }
}
