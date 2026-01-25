# PC Servis Manager
>## Popis
> **Tento program slúži na správu PC servisu, kde je možné pracovať s komponentmi PC (CPU, RAM, SSD, GPU, MB, ZDROJ, CASE). Program umožňuje modelovanie skladu komponentov, manuálne a automatické vytváranie PC zostáv, predaj zostáv (odstránenie komponentov zo skladu) a analytiku v podobe výpisov zostáv a inventára podľa kategórií.
Program je napísaný v jazyku Java a je spustiteľný v prostredí IntelliJ IDEA. Používa textové menu na ovládanie funkcionalít. Všetky operácie sú realizované cez konzolu.**

>## Inštalácia a spustenie
> **Stiahnite si projekt z GitHub repozitára.
Otvorte projekt v IntelliJ IDEA.
Spustite hlavnú triedu Main.java (obsahuje metódu main).
Program sa spustí v konzole a zobrazí textové menu.**  
>*Žiadne ďalšie závislosti nie sú potrebné (používa štandardné Java knižnice).* 

>## Funkcionality 
> **Manuálne vytvorenie PC zostavy:** *Používateľ vyberá komponenty zo skladu*.
> 
>**Automatické vytvorenie zostavy:** *Generuje zostavu do zadanej maximálnej sumy (náhodný výber komponentov z každej kategórie, kým sa neprekročí budget)*
> 
>**Predaj zostavy:** *Odstráni vybrané komponenty zo skladu.*
> 
>**Výpis zostavy:>  *Zobrazí detaily zostavy.*
>
>**Výpis inventára:** *Zobrazí komponenty podľa kategórií.*
>
>**Ukončenie programu:** *Možnosť exit.*

> ## Štruktúra projektu
> **Main.java:** Štartuje všetko, pridá testovacie diely.
>
> **model/:** Tu sú "veci" ako:
>
>> **Kategoria.java:** Zoznam typov dielov (CPU, RAM...).
>>
>> **PCKomponent.java:** Jeden diel (názov + cena + typ)
>> 
>> **PCZostava.java:** Tvoja zostava (zoznam dielov).
>>
>> **Sklad.java:** Sklad (zoznam dielov s počtom kusov).
>>
>> **PCServis.java:** Hlavný manažér (pridáva, odoberá, stavá).
>
> **ui/TextoveUI.java:** Menu v texte, kde píšeš čísla.

> ## UML Class Diagram
>
> https://mermaid.ai/app/projects/913eb1f7-f572-4652-aaff-016fd66ef545/diagrams/34a411f0-524c-4766-aa17-af76a83b6edb/version/v0.1/edit
> 
> https://mermaid.ai/app/projects/913eb1f7-f572-4652-aaff-016fd66ef545/diagrams/1b78c0b8-7784-415e-8432-f0da8e001477/version/v0.1/edit




