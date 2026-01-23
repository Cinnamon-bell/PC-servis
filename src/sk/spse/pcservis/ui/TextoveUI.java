package sk.spse.pcservis.ui;

import sk.spse.pcservis.model.Kategoria;
import sk.spse.pcservis.model.PCKomponent;
import sk.spse.pcservis.model.PCServis;
import sk.spse.pcservis.model.Sklad;

import java.util.HashMap;
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
            System.out.println(String.format("---%s---",  kat.toString()));
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
                }
                case 2 -> {
                    System.out.println("\nVybral si 'Automaticky vytvoriť zostavu do rozpočtu'\n");
                }
                case 3 -> {
                    System.out.println("\nVybral si 'Predať aktuálnu zostavu'\n");
                }
                case 4 -> {
                    System.out.println("\nVybral si 'Zobraziť aktuálnu zostavu'\n");
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