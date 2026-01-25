package sk.spse.pcservis.model;

import java.util.*;

public class PCZostava {

    private List<PCKomponent> komponenty;

    // KONŠTRUKTOR – vytvorenie prázdnej PC zostavy
    public PCZostava() {
        this.komponenty = new ArrayList<>();
    }

    // Manuálne pridanie komponentu do zostavy
    public boolean pridajKomponent(PCKomponent komponent) {

        // kontrola, či už existuje komponent rovnakej kategórie
        for (PCKomponent k : komponenty) {
            if (k.getKategoria() == komponent.getKategoria()) {
                return false; // napr. už existuje CPU
            }
        }

        komponenty.add(komponent);
        return true;
    }

    // Odstránenie komponentu zo zostavy
    public boolean odstranKomponent(Kategoria kategoria) {
        for (PCKomponent k : komponenty) {
            if (k.getKategoria() == kategoria) {
                komponenty.remove(k);
                return true;
            }
        }
        return false;
    }

    // Získanie zoznamu komponentov
    public List<PCKomponent> getKomponenty() {
        return komponenty;
    }

    // Výpočet celkovej ceny zostavy
    public double getCelkovaCena() {
        double suma = 0;
        for (PCKomponent k : komponenty) {
            suma += k.getCena();
        }
        return suma;
    }

}
