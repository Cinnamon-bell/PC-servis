package sk.spse.pcservis.model;

import java.util.*;

public class PCZostava {

    private List<PCKomponent> komponenty = new ArrayList<>();

    public void pridajKomponent(PCKomponent komponent) {
        komponenty.add(komponent);
    }

    public List<PCKomponent> getKomponenty() {
        return komponenty;
    }

    public double getCelkovaCena() {
        double suma = 0;
        for (PCKomponent k : komponenty) {
            suma += k.getCena();
        }
        return suma;
    }

    public void vypisZostavu() {
        System.out.println("=== PC ZOSTAVA ===");
        for (PCKomponent k : komponenty) {
            System.out.println(k);
        }
        System.out.println("Celková cena: " + getCelkovaCena() + " €");
    }
}

