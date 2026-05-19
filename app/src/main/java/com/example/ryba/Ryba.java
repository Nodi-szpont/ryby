package com.example.ryba;

public class Ryba {
    private String nazwa;
    private double waga;
    private int dlugosc;
    private String odzywianie;
    private boolean okresOchronny;

    public Ryba(String nazwa, double waga, int dlugosc, String odzywianie, boolean okresOchronny) {
        this.nazwa = nazwa;
        this.waga = waga;
        this.dlugosc = dlugosc;
        this.odzywianie = odzywianie;
        this.okresOchronny = okresOchronny;
    }

    public String getNazwa() { return nazwa; }
    public double getWaga() { return waga; }
    public int getDlugosc() { return dlugosc; }
    public String getOdzywianie() { return odzywianie; }
    public boolean isOkresOchronny() { return okresOchronny; }
}