package sk.spse.pcservis.model;

import java.util.*;

public class Sklad {

    private Map<PCKomponent, Integer> inventar = new HashMap<>();

    public void pridajKomponent(PCKomponent komponent, int pocet) {
        inventar.put(
                komponent,
                inventar.getOrDefault(komponent, 0) + pocet
        );
    }

    public boolean odstranKomponent(PCKomponent komponent) {
        if (!inventar.containsKey(komponent)) {
            return false;
        }

        int pocet = inventar.get(komponent);
        if (pocet > 1) {
            inventar.put(komponent, pocet - 1);
        } else {
            inventar.remove(komponent);
        }
        return true;
    }

    public Map<PCKomponent, Integer> getInventar() {
        return inventar;
    }

    public Map<PCKomponent, Integer> getKomponentyPodlaKategorie(Kategoria kategoria) {
        Map<PCKomponent, Integer> vysledok = new HashMap<>();

        for (Map.Entry<PCKomponent, Integer> entry : inventar.entrySet()) {
            if (entry.getKey().getKategoria() == kategoria) {
                vysledok.put(entry.getKey(), entry.getValue());
            }
        }
        return vysledok;
    }
}
