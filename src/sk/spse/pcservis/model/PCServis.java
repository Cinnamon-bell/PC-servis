package sk.spse.pcservis.model;
import java.util.*;

public class PCServis {

    private Sklad sklad;

    public PCServis(Sklad sklad) {
        this.sklad = sklad;
    }

    public PCZostava vytvorZostavuAutomaticky(double maxSuma) {
        PCZostava zostava = new PCZostava();
        double suma = 0;

        for (Kategoria kategoria : Kategoria.values()) {
            Map<PCKomponent, Integer> komponenty =
                    sklad.getKomponentyPodlaKategorie(kategoria);

            for (PCKomponent k : komponenty.keySet()) {
                if (suma + k.getCena() <= maxSuma) {
                    zostava.pridajKomponent(k);
                    suma += k.getCena();
                    break; // 1 komponent z kategórie
                }
            }
        }
        return zostava;
    }

    public void predajZostavu(PCZostava zostava) {
        for (PCKomponent k : zostava.getKomponenty()) {
            sklad.odstranKomponent(k);
        }
    }

    public void vypisInventar() {
        System.out.println("=== INVENTÁR ===");
        for (Kategoria kategoria : Kategoria.values()) {
            System.out.println("-- " + kategoria + " --");
            Map<PCKomponent, Integer> mapa =
                    sklad.getKomponentyPodlaKategorie(kategoria);

            for (Map.Entry<PCKomponent, Integer> entry : mapa.entrySet()) {
                System.out.println(entry.getKey() + " | kusov: " + entry.getValue());
            }
        }
    }
}

