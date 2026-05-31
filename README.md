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
    Start[Start: ulozit()] --> Load[Načítanie hodnôt z polí]
    Load --> CheckRequired{Všetky povinné polia vyplnené?}
    
    CheckRequired -->|Nie| Error1[Zobraziť chybu: Vyplňte všetky polia]
    Error1 --> End[Koniec]
    
    CheckRequired -->|Áno| ParsePrice[Parsovanie ceny]
    ParsePrice --> CheckPrice{Úspešné parsovanie?}
    
    CheckPrice -->|Nie| Error2[Zobraziť chybu: Neplatná cena]
    Error2 --> End
    
    CheckPrice -->|Áno| CheckCount{Kladné celé číslo?}
    
    CheckCount -->|Nie| Error3[Zobraziť chybu: Neplatné množstvo]
    Error3 --> End
    
    CheckCount -->|Áno| Create[ Vytvoriť PCKomponent ]
    Create --> AddToStorage[Pridať do skladu]
    AddToStorage --> Refresh[Refresh UI]
    Refresh --> Close[Zavrieť okno]
    Close --> End
```




