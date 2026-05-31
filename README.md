# PC Servis Manager

**Aplikácia na správu skladu PC komponentov a zostavovanie počítačových konfigurácií**

---

## Popis projektu

Tento program slúži na správu PC servisu, kde je možné pracovať s komponentmi PC (**CPU, RAM, SSD, GPU, MB, ZDROJ, CASE**).

Program umožňuje:
- Modelovanie skladu komponentov
- Manuálne vytváranie PC zostáv
- Automatické generovanie zostáv podľa zadaného rozpočtu
- Predaj zostáv 
- Analytiku – výpisy zostáv a inventára podľa kategórií

**Aktuálna verzia používa grafické užívateľské rozhranie (JavaFX)**.

---

## Technológie

- **Jazyk:** Java
- **GUI:** JavaFX + FXML
- **Štruktúra:** Objektovo-orientovaný prístup (triedy `Controller`, `AddComponentController`, `SkladProvider`, atď.)

---


## Hlavné funkcionality

- **Správa skladu** – prehľad komponentov v tabuľke a stromovom zobrazení, pridávanie nových komponentov
- **Manuálne vytvorenie PC zostavy** – výber komponentov podľa kategórií
- **Automatické vytvorenie zostavy** – generovanie optimálnej konfigurácie podľa zadaného maximálneho rozpočtu
- **Výpis zostavy** – detailný prehľad vybranej konfigurácie a celkovej ceny
- **Výpis inventára** – zobrazenie komponentov podľa kategórií

---
## Inštalácia a spustenie


```bash
# Spustiť hlavnú aplikáciu
java sk.spse.pcservis.ui.UIMain
```

## Vývojový diagram metódy ulozit()
```mermaid
flowchart TD
    A[Začiatok: ulozit()] --> B[Načítať hodnoty z polí]
    B --> C{nazov, cena, kategoria, pocet vyplnené?}
    
    C -->|Nie| D[Zobraziť chybu: 'Vyplňte všetky povinné polia!']
    D --> Z[Konec]
    
    C -->|Áno| E[Očistiť a parsovať cenu]
    E --> F{Úspešné parsovanie ceny?}
    
    F -->|Nie| G[Zobraziť chybu: 'Zadajte kladné číslo!']
    G --> Z
    
    F -->|Áno| H{Pocet je celé kladné číslo?}
    
    H -->|Nie| I[Zobraziť chybu: 'Zadajte celé kladné číslo!']
    I --> Z
    
    H -->|Áno| J[Vytvoriť objekt PCKomponent]
    J --> K[Pridať komponent do skladu cez sklad.pridajKomponent()]
    K --> L[Spustiť callback refresh (ak existuje)]
    L --> M[Zavrieť okno]
    M --> Z[Konec]

    style A fill:#4ade80
    style Z fill:#4ade80
```




