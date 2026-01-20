package sk.spse.pcservis.model;

import java.util.Objects;

public class PCKomponent {

    private String nazov;
    private double cena;
    private Kategoria kategoria;

    public PCKomponent(String nazov, double cena, Kategoria kategoria) {
        this.nazov = nazov;
        this.cena = cena;
        this.kategoria = kategoria;
    }

    public String getNazov() {
        return nazov;
    }

    public double getCena() {
        return cena;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    @Override
    public String toString() {
        return nazov + " | " + kategoria + " | " + cena + " €";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PCKomponent)) return false;
        PCKomponent that = (PCKomponent) o;
        return Objects.equals(nazov, that.nazov)
                && kategoria == that.kategoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazov, kategoria);
    }
}
