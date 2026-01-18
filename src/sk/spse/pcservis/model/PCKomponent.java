package sk.spse.pcservis.model;

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
}

