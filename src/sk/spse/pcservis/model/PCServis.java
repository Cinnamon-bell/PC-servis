package sk.spse.pcservis.model;
import java.util.*;

public class PCServis {

    private Sklad sklad;
    private PCZostava zostava = new PCZostava();

    public PCServis(Sklad sklad) {
        this.sklad = new Sklad();
    }

    public PCZostava vytvorZostavuAutomaticky(double maxSuma) {

        PCZostava zostava = new PCZostava();

        Kategoria[] kategorie = Kategoria.values();
        int pocetKategorii = kategorie.length;

        double zostavajuciBudget = maxSuma;
        double zakladnyPodiel = maxSuma / pocetKategorii;

        double zostatok = 0;

        for (Kategoria kategoria : kategorie) {

            double aktualnyBudget = zakladnyPodiel + zostatok;

            Map<PCKomponent, Integer> komponenty =
                    sklad.getKomponentyPodlaKategorie(kategoria);

            List<PCKomponent> zoradene =
                    new ArrayList<>(komponenty.keySet());

            zoradene.sort(
                    Comparator.comparingDouble(PCKomponent::getCena).reversed()
            );

            PCKomponent vybrany = null;

            for (PCKomponent k : zoradene) {
                if (k.getCena() <= aktualnyBudget) {
                    vybrany = k;
                    break;
                }
            }

            if (vybrany != null) {
                zostava.pridajKomponent(vybrany);

                zostatok = aktualnyBudget - vybrany.getCena();
                zostavajuciBudget -= vybrany.getCena();
            }
            else {
                zostatok += zakladnyPodiel;
            }
        }

        return zostava;
    }

    public Sklad getSklad() {
        return sklad;
    }

    public PCZostava getZostava() {
        return zostava;
    }

    public boolean setZostava(PCZostava zostava){
        this.zostava = zostava;
        return true;
    }

    public void pridajKomponent(PCKomponent komponent) {
        sklad.pridajKomponent(komponent,1);
    }
    public void odoberKomponent(PCKomponent komponent) { sklad.odstranKomponent(komponent);}

    public void predajZostavu(PCZostava zostava) {
        for (PCKomponent k : zostava.getKomponenty()) {
            sklad.odstranKomponent(k);
        }
    }

    public boolean odoberKomponent(Kategoria kat) {
        return zostava.odstranKomponent(kat);
    }

    public Map<PCKomponent, Integer> getKomponentyPodlaKategorie(Kategoria kategoria) {
        return sklad.getKomponentyPodlaKategorie(kategoria);
    }

    public Map<PCKomponent, Integer> getInventar() {
        return sklad.getInventar();
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