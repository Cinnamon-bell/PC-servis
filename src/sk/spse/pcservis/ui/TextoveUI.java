package sk.spse.pcservis.ui;

import sk.spse.pcservis.model.Kategoria;
import sk.spse.pcservis.model.PCKomponent;
import sk.spse.pcservis.model.PCServis;
import sk.spse.pcservis.model.Sklad;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class TextoveUI {
    private PCServis pcServis;
    private Scanner sc = new Scanner(System.in);
    private Map<PCKomponent, Integer> inventar;
    public TextoveUI(Sklad sklad) {
        this.pcServis= new PCServis(sklad);
        this.inventar = pcServis.getInventar();
    }

    private void vypisHlavneMenu() {
        System.out.println("--- MENU ---");
        System.out.println("1. Manuálne vytvoriť zostavu");
        System.out.println("2. Automaticky vytvoriť zostavu do rozpočtu");
        System.out.println("3. Predať aktuálnu zostavu");
        System.out.println("4. Zobraziť aktuálnu zostavu");
        System.out.println("5. Výpis celého skladu");
        System.out.println("6. Výpis skladu podľa kategórie");
        System.out.println("7. Koniec");
        System.out.print("Voľba: ");
    }

    public void VypisSklad (){
        System.out.println("--- SKLAD ---");
        for (Kategoria kat: Kategoria.values()) {
            System.out.println(String.format("\n---%s---",  kat.toString()));
            Map<PCKomponent, Integer> inventarKat = this.pcServis.getKomponentyPodlaKategorie(kat);
            for(Map.Entry<PCKomponent, Integer> entry: inventarKat.entrySet()){
                System.out.println(String.format("%s - %s ks",  entry.getKey().toString(), entry.getValue()));
            };
        }
        while(true){
            int volba;
            System.out.println("1. Navrat do menu");
            try{
                volba = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Zadajte číslo!");
                sc.nextLine();
                continue;
            }

            switch (volba){
                case 1 -> {return;}
                default -> System.out.println("Neplatná voľba");
            }
        }
    }

    public boolean manualZostavaKomponenty(Kategoria kategoria) {
        while(true){
            System.out.println(String.format("--- %s ---",  kategoria.toString()));
            System.out.println("Vyberte si komponent");
            Map<PCKomponent, Integer> inventarKat = this.pcServis.getKomponentyPodlaKategorie(kategoria);
            int numb = 1;
            for(Map.Entry<PCKomponent, Integer> entry: inventarKat.entrySet()){
                System.out.println(String.format("%s"+". "+"%s - %s ks", numb++ ,entry.getKey().toString(), entry.getValue()));
            };
            System.out.println(String.format("\n%s"+". Návrat do kategorií", inventarKat.size()+1));
            System.out.println(String.format("%s"+". Vypisat aktualnu zostavu", inventarKat.size()+2));
            System.out.println(String.format("%s"+". Návrat do menu", inventarKat.size()+3));
            int volba = 0;
            try{
                volba = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Zadajte číslo!");
                sc.nextLine();
            }
            if (volba == inventarKat.size()+1){
                return true;
            }else if (volba == inventarKat.size()+2){
                vypisZostavu();
                continue;
            }else if (volba == inventarKat.size()+3){
                return false;
            }
            for (Map.Entry<PCKomponent, Integer> entry: inventarKat.entrySet()){
                if (entry.getKey().toString().equals(inventarKat.entrySet().toArray()[volba-1].toString().substring(0,inventarKat.entrySet().toArray()[volba-1].toString().indexOf("=")))) {
                    if (!pcServis.getZostava().pridajKomponent(entry.getKey())) {
                        System.out.println("Komponent tejot kategorie je už v zostave");
                    }else {
                        vypisZostavu();
                    }
                }


            }
        }

    }



    public void vypisZostavu(){
        System.out.println("---Aktuálna zostava---\n");
        for (PCKomponent komp : pcServis.getZostava().getKomponenty()) {
            System.out.println(String.format("--- %s---",  komp.getKategoria().toString()));
            System.out.println(komp.toString()+"\n");
        }
        if (pcServis.getZostava().getKomponenty().size()==0){
            System.out.println("---V zostave zatial nemas ziadne komponenty---\n");
        }
        System.out.println("-------------------------------------");
    }

    public void odoberKomponent(PCKomponent komponent){

    }

    public void manualZostavaKategorie() {
        Kategoria kat = Kategoria.CPU;
        boolean backToMenu = true;
        System.out.println("----Manualna tvorba zostavy----\n");
        System.out.println("--- Vyberte si kategoriu ---");
        for (int i = 0; i < Kategoria.values().length; i++) {
            System.out.println(String.format("%s %s",Integer.toString(i+1)+".", Kategoria.values()[i]));
        }
        System.out.println("\n"+(Kategoria.values().length+1)+"."+" Naspäť do menu");
        while(backToMenu){
            int volba;
            try{
                volba= sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e) {
                System.out.println("Zadajte číslo!");
                sc.nextLine();
                continue;
            }


            if (volba == Kategoria.values().length+1){
                return;
            }else if (volba >= 1 && volba <= Kategoria.values().length) {
                kat = Kategoria.values()[volba - 1];
            }
            else {
                System.out.println("Nespravna volba");
                continue;
            }
            loop:
            for (Kategoria i  : Kategoria.values()){
                if (kat.equals(i)){
                    backToMenu =  manualZostavaKomponenty(kat);
                    break loop;
                }
            }

            System.out.println("--- Vyberte si kategoriu ---");
            for (int i = 0; i < Kategoria.values().length; i++) {
                System.out.println(String.format("%s %s",Integer.toString(i+1)+".", Kategoria.values()[i]));
            }
            System.out.println("\n"+(Kategoria.values().length+1)+"."+ "Odstrániť komponent zo zostavy");
            System.out.println("\n"+(Kategoria.values().length+2)+"."+" Naspäť do menu");
        }
    }

    public void pridajKomponent(PCKomponent zostava) {
        pcServis.pridajKomponent((zostava));
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
                    System.out.println("\nVybral si 'Automaticky vytvoriť zostavu do rozpočtu'\n");
                }
                case 3 -> {
                    System.out.println("\nVybral si 'Predať aktuálnu zostavu'\n");
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