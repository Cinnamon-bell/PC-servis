package sk.spse.pcservis;

import sk.spse.pcservis.model.Kategoria;
import sk.spse.pcservis.model.PCKomponent;
import sk.spse.pcservis.model.Sklad;

import sk.spse.pcservis.ui.TextoveUI;



public class Main {
    public static void spustit() {
        Sklad sklad = new Sklad();

        TextoveUI ui = new TextoveUI(sklad);

        ui.pridajKomponent(new PCKomponent("Intel Core i5-14400F", 185.00, Kategoria.CPU));
        ui.pridajKomponent(new PCKomponent("AMD Ryzen 5 7600", 205.00, Kategoria.CPU));


        ui.pridajKomponent(new PCKomponent("Kingston Fury 16GB DDR5-6000", 68.00, Kategoria.RAM));
        ui.pridajKomponent(new PCKomponent("Corsair Vengeance 32GB DDR5-6000", 135.00, Kategoria.RAM));

        ui.pridajKomponent(new PCKomponent("Kingston NV2 1TB PCIe 4.0", 62.00, Kategoria.SSD));
        ui.pridajKomponent(new PCKomponent("WD Black SN850X 1TB", 98.00, Kategoria.SSD));

        ui.pridajKomponent(new PCKomponent("NVIDIA GeForce RTX 4060 8GB", 295.00, Kategoria.GPU));
        ui.pridajKomponent(new PCKomponent("AMD Radeon RX 7600 8GB", 275.00, Kategoria.GPU));

        ui.pridajKomponent(new PCKomponent("MSI B760 Gaming Plus WiFi", 145.00, Kategoria.MB));
        ui.pridajKomponent(new PCKomponent("ASUS PRIME B650-PLUS", 158.00, Kategoria.MB));

        ui.pridajKomponent(new PCKomponent("Corsair CX550M 550W", 68.00, Kategoria.ZDROJ));
        ui.pridajKomponent(new PCKomponent("Corsair RM750x 750W 80+ Gold", 115.00, Kategoria.ZDROJ));

        ui.pridajKomponent(new PCKomponent("Corsair 4000D Airflow", 95.00, Kategoria.CASE));
        ui.pridajKomponent(new PCKomponent("Lian Li Lancool 216", 105.00, Kategoria.CASE));


        ui.spustitRozhranie();
    }
    public static void main(String[] args) {
        spustit();
    }
}