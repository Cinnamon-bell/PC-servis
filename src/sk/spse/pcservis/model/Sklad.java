package sk.spse.pcservis.model;

import java.util.*;

public class Sklad {

    private List<PCKomponent> komponenty = new ArrayList<>();

    public void pridajKomponent(PCKomponent komponent) {
        komponenty.add(komponent);
    }

    public void odstranKomponent(PCKomponent komponent) {
        komponenty.remove(komponent);
    }

    public List<PCKomponent> getKomponenty() {
        return komponenty;
    }

    public List<PCKomponent> getKomponentyPodlaKategorie(Kategoria kategoria) {
        List<PCKomponent> vysledok = new ArrayList<>();
        for (PCKomponent k : komponenty) {
            if (k.getKategoria() == kategoria) {
                vysledok.add(k);
            }
        }
        return vysledok;
    }
}

