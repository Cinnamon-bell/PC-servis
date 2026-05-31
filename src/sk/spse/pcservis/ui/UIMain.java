package sk.spse.pcservis.ui;

import sk.spse.pcservis.model.Kategoria;
import sk.spse.pcservis.model.PCKomponent;
import sk.spse.pcservis.model.Sklad;

public class UIMain {

    private final Sklad sklad = new Sklad();

    public UIMain() {
        add("Intel Core i3-12100F",            99.00,  Kategoria.CPU, 4);
        add("Intel Core i5-14400F",           185.00,  Kategoria.CPU, 3);
        add("Intel Core i7-14700K",           385.00,  Kategoria.CPU, 2);
        add("Intel Core i9-14900K",           545.00,  Kategoria.CPU, 1);
        add("AMD Ryzen 5 5600X",              145.00,  Kategoria.CPU, 3);
        add("AMD Ryzen 5 7600",               205.00,  Kategoria.CPU, 3);
        add("AMD Ryzen 7 7700X",              315.00,  Kategoria.CPU, 2);
        add("AMD Ryzen 9 7950X",              649.00,  Kategoria.CPU, 1);

        add("Kingston Fury Beast 16GB DDR4-3200",   38.00, Kategoria.RAM, 5);
        add("Corsair Vengeance 16GB DDR4-3600",     45.00, Kategoria.RAM, 4);
        add("Kingston Fury 16GB DDR5-6000",         68.00, Kategoria.RAM, 4);
        add("Corsair Vengeance 32GB DDR5-6000",    135.00, Kategoria.RAM, 3);
        add("G.Skill Trident Z5 32GB DDR5-6400",   155.00, Kategoria.RAM, 2);
        add("G.Skill Ripjaws 64GB DDR5-5600",      245.00, Kategoria.RAM, 1);

        add("Kingston A400 480GB SATA",             35.00, Kategoria.SSD, 6);
        add("Samsung 870 EVO 1TB SATA",             75.00, Kategoria.SSD, 4);
        add("Kingston NV2 1TB PCIe 4.0",            62.00, Kategoria.SSD, 5);
        add("WD Blue SN570 1TB PCIe 3.0",           55.00, Kategoria.SSD, 4);
        add("WD Black SN850X 1TB PCIe 4.0",         98.00, Kategoria.SSD, 3);
        add("Samsung 990 Pro 2TB PCIe 4.0",        175.00, Kategoria.SSD, 2);
        add("Seagate FireCuda 530 2TB PCIe 4.0",   195.00, Kategoria.SSD, 1);

        add("AMD Radeon RX 6600 8GB",              195.00, Kategoria.GPU, 3);
        add("NVIDIA GeForce RTX 3060 12GB",        235.00, Kategoria.GPU, 3);
        add("AMD Radeon RX 7600 8GB",              275.00, Kategoria.GPU, 3);
        add("NVIDIA GeForce RTX 4060 8GB",         295.00, Kategoria.GPU, 3);
        add("NVIDIA GeForce RTX 4060 Ti 16GB",     435.00, Kategoria.GPU, 2);
        add("AMD Radeon RX 7900 GRE 16GB",         495.00, Kategoria.GPU, 2);
        add("NVIDIA GeForce RTX 4070 Super 12GB",  565.00, Kategoria.GPU, 1);
        add("NVIDIA GeForce RTX 4080 Super 16GB",  975.00, Kategoria.GPU, 1);

        add("ASRock B660M Pro RS DDR4",            105.00, Kategoria.MB, 3);
        add("MSI B760 Gaming Plus WiFi",           145.00, Kategoria.MB, 3);
        add("ASUS PRIME B760-PLUS DDR4",           125.00, Kategoria.MB, 3);
        add("ASUS PRIME B650-PLUS",                158.00, Kategoria.MB, 3);
        add("MSI MAG X670E Tomahawk WiFi",         275.00, Kategoria.MB, 2);
        add("ASUS ROG Strix B760-F Gaming WiFi",   255.00, Kategoria.MB, 2);
        add("ASUS ROG Maximus Z790 Hero",          595.00, Kategoria.MB, 1);

        add("Seasonic S12III 500W 80+ Bronze",      55.00, Kategoria.ZDROJ, 4);
        add("Corsair CX550M 550W 80+ Bronze",       68.00, Kategoria.ZDROJ, 4);
        add("be quiet! Pure Power 11 600W",         79.00, Kategoria.ZDROJ, 3);
        add("Corsair RM750x 750W 80+ Gold",        115.00, Kategoria.ZDROJ, 3);
        add("EVGA SuperNOVA 850W G6 80+ Gold",     145.00, Kategoria.ZDROJ, 2);
        add("Seasonic FOCUS GX-1000W 80+ Gold",    185.00, Kategoria.ZDROJ, 1);

        add("Fractal Design Core 1000",             45.00, Kategoria.CASE, 4);
        add("NZXT H510",                            79.00, Kategoria.CASE, 3);
        add("Corsair 4000D Airflow",                95.00, Kategoria.CASE, 3);
        add("Lian Li Lancool 216",                 105.00, Kategoria.CASE, 2);
        add("be quiet! Pure Base 500DX",           115.00, Kategoria.CASE, 2);
        add("Fractal Design Meshify 2",            135.00, Kategoria.CASE, 2);
        add("Lian Li O11 Dynamic EVO",             145.00, Kategoria.CASE, 1);
    }

    private void add(String nazov, double cena, Kategoria kat, int pocet) {
        sklad.pridajKomponent(new PCKomponent(nazov, cena, kat), pocet);
    }

    public static void main(String[] args) {
        UIMain runner = new UIMain();
        SkladProvider.setSklad(runner.sklad);
        javafx.application.Application.launch(sk.spse.pcservis.ui.Application.class, args);
    }
}