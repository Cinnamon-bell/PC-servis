package sk.spse.pcservis.ui;

import sk.spse.pcservis.model.*;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class TextoveUI {
    final private PCServis pcServis;
    final private Scanner sc = new Scanner(System.in);
    public TextoveUI(Sklad sklad) {
        this.pcServis= new PCServis(sklad);
    }

    private void vypisHlavneMenu() {
        System.out.println("--- MENU ---");
        System.out.println("1. Manuálne vytvoriť zostavu");
        System.out.println("2. Automaticky vytvoriť zostavu do rozpočtu");
        System.out.println("3. Kúpiť aktuálnu zostavu");
        System.out.println("4. Zobraziť aktuálnu zostavu");
        System.out.println("5. Výpis celého skladu");
        System.out.println("6. Výpis skladu podľa kategórie");
        System.out.println("7. Koniec");
        System.out.print("Voľba: ");
    }

    public void VypisSklad (){
        while(true){
        System.out.println("--- SKLAD ---");
        for (Kategoria kat: Kategoria.values()) {
            System.out.printf("\n---%s---%n", kat.toString());
            if (!pcServis.getKomponentyPodlaKategorie(kat).isEmpty()) {
                Map<PCKomponent, Integer> inventarKat = this.pcServis.getKomponentyPodlaKategorie(kat);
                for (Map.Entry<PCKomponent, Integer> entry : inventarKat.entrySet()) {
                    System.out.printf("%s - %s ks%n", entry.getKey().toString(), entry.getValue());
                }
            }else {
                System.out.println("---Z tejto kategorie nemame ziadny komponent na sklade---");
            }
        }
            System.out.println("1. Navrat do menu");
            System.out.print("Voľba: ");
            int volba;
            try{
                volba = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Zadajte číslo!");
                sc.nextLine();
                continue;
            }

            if (volba == 1) {
                return;
            } else {
                System.out.println("Neplatná voľba");
            }
        }
    }
    public void KupaZostavy(){
        for (PCKomponent k: pcServis.getZostava().getKomponenty()){
            pcServis.odoberKomponent(k);
        }
        System.out.println("Dakujeme za kupu");
    }
    public void VypisSkladuKat(){
        while (true){
            System.out.println("----Kategorie na sklade----");
            for (int i = 0; i < Kategoria.values().length; i++) {
                System.out.printf("%s %s%n", i + 1 +".", Kategoria.values()[i]);
            }
            System.out.println("\n"+(Kategoria.values().length+1)+". "+"Naspät do menu");
            System.out.print("Voľba: ");
            int volba;
            try{
                volba = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Nesprávna voľba");
                sc.nextLine();
                continue;
            }
            if (volba <= Kategoria.values().length){
                System.out.printf("\n---%s---%n",  Kategoria.values()[volba-1]);
                Map<PCKomponent, Integer> inventarKat;
                inventarKat = pcServis.getKomponentyPodlaKategorie(Kategoria.values()[volba-1]);
                for (Map.Entry<PCKomponent, Integer> entry: inventarKat.entrySet()){
                    System.out.printf("%s - %s ks%n",  entry.getKey().toString(), entry.getValue());
                }
                System.out.println("-------------------------\n");
            }else if (volba == Kategoria.values().length+1){
                return;
            }
            else {
                System.out.println("Nespravna volba");
            }
        }
    }

    public boolean manualZostavaKomponenty(Kategoria kategoria) {
        while(true){
            System.out.printf("--- %s ---%n",  kategoria.toString());
            System.out.println("Vyberte si komponent");
            Map<PCKomponent, Integer> inventarKat = this.pcServis.getKomponentyPodlaKategorie(kategoria);
            int numb = 1;
            for(Map.Entry<PCKomponent, Integer> entry: inventarKat.entrySet()){
                System.out.printf("%s"+". "+ "%s - %s ks%n", numb++ ,entry.getKey().toString(), entry.getValue());
            }
            System.out.printf("\n%s"+ ". Návrat do kategorií%n", inventarKat.size()+1);
            System.out.printf("%s"+ ". Vypisat aktualnu zostavu%n", inventarKat.size()+2);
            System.out.printf("%s"+ ". Návrat do menu%n", inventarKat.size()+3);
            System.out.print("Voľba: ");
            int volba = 0;
            try{
                volba = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Zadajte číslo!");
                sc.nextLine();
            }
            if (volba != 0 && volba <= inventarKat.size()+3) {
                if (volba == inventarKat.size() + 1) {
                    return true;
                } else if (volba == inventarKat.size() + 2) {
                    vypisZostavu();
                    continue;
                } else if (volba == inventarKat.size() + 3) {
                    return false;
                }
                for (Map.Entry<PCKomponent, Integer> entry : inventarKat.entrySet()) {
                    if (entry.getKey().toString().equals(inventarKat.entrySet().toArray()[volba - 1].toString().substring(0, inventarKat.entrySet().toArray()[volba - 1].toString().indexOf("=")))) {
                        if (!pcServis.getZostava().pridajKomponent(entry.getKey())) {
                            System.out.println("Komponent tejot kategorie je už v zostave");
                        } else {
                            System.out.println("Komponent bol pridany do zostavy");
                            return true;
                        }
                    }
                }
            }else {
                System.out.println("Nespravna volba");
            }
        }

    }



    public void vypisZostavu(){
        while(true){
            System.out.println("---Aktuálna zostava---\n");
            for (PCKomponent komp : pcServis.getZostava().getKomponenty()) {
                System.out.printf("--- %s---%n",  komp.getKategoria().toString());
                System.out.println(komp +"\n");
            }
            if (pcServis.getZostava().getKomponenty().isEmpty()){
                System.out.println("---V zostave zatial nemas ziadne komponenty---\n");
            }
            System.out.println("-------------------------------------");
            System.out.println("1. Odstrániť komponent zo zostavy");
            System.out.println("2. Vyčistiť zostavu");
            System.out.println("3. Naspäť");
            System.out.print("Voľba: ");
            int volba;
            try{
                volba = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Nespravna volba");
                sc.nextLine();
                continue;
            }
            switch (volba){
                case 1 -> odoberKomponent();
                case 2 -> {
                    if (pcServis.setZostava(new PCZostava())){
                        System.out.println("Vycistil som aktualnu zostavu");
                    }
                }
                case 3 -> {return;}
                default ->  System.out.println("Nespravna volba");
            }
        }
    }

    public void odoberKomponent() {
        while (true){
            if (pcServis.getZostava().getKomponenty().isEmpty()){
                System.out.println("V zostave nemas ziadne komponenty na odstranenie");
                break;
            }
            System.out.println("-----Odstranenie komponentu zo zostavy-----");
            System.out.println("Vyber kategoriu z ktorej sa ma odstranit komponent");
            for (int i = 0; i < pcServis.getZostava().getKomponenty().size(); i++) {
                System.out.printf("%s %s%n", i + 1 +".", pcServis.getZostava().getKomponenty().get(i).getKategoria().toString());
            }
            System.out.println("\n"+(pcServis.getZostava().getKomponenty().size()+1)+"."+ " Naspäť");
            System.out.print("Voľba: ");
            int volba;
            try{
                volba = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Zadajte číslo!");
                sc.nextLine();
                continue;
            }
            if (volba == pcServis.getZostava().getKomponenty().size()+1){
                break;
            }else if (volba > pcServis.getZostava().getKomponenty().size()+1){
                System.out.println("Nespravna volba");
            }
            Kategoria kat = Kategoria.values()[volba-1];
            if (pcServis.odoberKomponent(kat)) {
                System.out.println("Komponentu bol zo zostavy odstraneni");
                break;
            }

        }
    }

    public void manualZostavaKategorie() {
        Kategoria kat = null;
        boolean backToMenu = true;
        while(backToMenu){
            System.out.println("----Manualna tvorba zostavy----\n");
            System.out.println("--- Vyberte si kategoriu ---");
            for (int i = 0; i < Kategoria.values().length; i++)
                System.out.printf("%s %s%n", i + 1 + ".", Kategoria.values()[i]);
            System.out.println("\n"+(Kategoria.values().length+1)+"."+ " Odstrániť komponent zo zostavy");
            System.out.println((Kategoria.values().length+2)+"."+" Vypisat aktualnu zostavu");
            System.out.println((Kategoria.values().length+3)+"."+" Naspäť do menu");
            System.out.print("Voľba: ");
            int volba;
            try{
                volba= sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e) {
                System.out.println("Zadajte číslo!");
                sc.nextLine();
                continue;
            }
            if (volba <= Kategoria.values().length+3) {
                if (volba == Kategoria.values().length + 3) {
                    return;
                } else if (volba == Kategoria.values().length + 2) {
                    vypisZostavu();
                    continue;
                } else if (volba >= 1 && volba <= Kategoria.values().length) {
                    kat = Kategoria.values()[volba - 1];
                } else if (volba == Kategoria.values().length + 1) {
                    odoberKomponent();
                    kat = null;
                }
                loop:
                if (kat != null) {
                    for (Kategoria i : Kategoria.values()) {
                        if (kat.equals(i)) {
                            backToMenu = manualZostavaKomponenty(kat);
                            break loop;
                        }
                    }
                }
            }
        }
    }

    public void pridajKomponent(PCKomponent zostava) {
        pcServis.pridajKomponent((zostava));
    }

    public void autoZostava(){
        while (true){
            System.out.println("----Auto zostava----");
            System.out.println("Vyberte sumu");
            System.out.print("Voľba: ");
            int volba = 0;
            try {
                volba = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Zadajte sumu");
                sc.nextLine();
                continue;
            }
            if(pcServis.setZostava(pcServis.vytvorZostavuAutomaticky((double)volba))){
                break;
            }
        }
    }


    public void spustitRozhranie(){
        System.out.println("=====================================");
        System.out.println("             PC SERVIS               ");
        System.out.println("=====================================\n");
        while(true){
            vypisHlavneMenu();
            int volba;
            try{
                volba = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Zadajte číslo!");
                sc.nextLine();
                continue;
            }

            switch (volba){
                case 1 -> {
                    System.out.println("\nVybral si 'Manuálne vytvoriť zostavu'\n");
                    manualZostavaKategorie();
                }
                case 2 -> {
                    autoZostava();
                }
                case 3 -> {
                    System.out.println("\nVybral si 'Kúpiť aktuálnu zostavu'\n");
                    KupaZostavy();
                }
                case 4 -> {
                    System.out.println("\nVybral si 'Zobraziť aktuálnu zostavu'\n");
                    vypisZostavu();
                }
                case 5 -> {
                    System.out.println("\nVybral si 'Výpis celého skladu'\n");
                    VypisSklad();
                }
                case 6 -> {
                    System.out.println("\nVybral si 'Výpis skladu podľa kategórie'\n");
                    VypisSkladuKat();
                }
                case 7 -> {
                    System.out.println("Koniec programu");
                    return;
                }
                default -> System.out.println("Neplatná voľba, skúste 1–7.");
            }
        }
    }

}