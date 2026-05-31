package sk.spse.pcservis.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sk.spse.pcservis.model.Kategoria;
import sk.spse.pcservis.model.PCKomponent;
import sk.spse.pcservis.model.Sklad;

/**
 * Controller pre add_component.fxml
 */
public class AddComponentController {

    @FXML private TextField   nazov_field;
    @FXML private ComboBox<String> kategoria_combo;
    @FXML private TextField   cena_field;
    @FXML private TextField   pocet_field;

    private Sklad    sklad;
    private Runnable callback; //používa sa aby sa aktualizoval hlavný interface

    public void setSkladACallback(Sklad sklad, Runnable callback) {
        this.sklad    = sklad;
        this.callback = callback;
    }

    @FXML
    public void ulozit() {
        String nazov = nazov_field.getText().trim();
        String katStr = kategoria_combo.getValue();

        if (nazov.isEmpty() || katStr == null || cena_field.getText().trim().isEmpty()) {
            nazov_field.setPromptText("Vyplňte všetky povinné polia!");
            return;
        }

        double cena;
        try {
            cena = Double.parseDouble(cena_field.getText().trim().replace(",", "."));
            if (cena < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            cena_field.clear();
            cena_field.setPromptText("Zadajte kladné číslo!");
            return;
        }

        int pocet = 1;
        try {
            String pocetText = pocet_field.getText().trim();
            if (!pocetText.isEmpty()) {
                pocet = Integer.parseInt(pocetText);
                if (pocet < 1) throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            pocet_field.clear();
            pocet_field.setPromptText("Zadajte celé kladné číslo!");
            return;
        }

        Kategoria kat = Kategoria.valueOf(katStr);
        sklad.pridajKomponent(new PCKomponent(nazov, cena, kat), pocet);

        if (callback != null) callback.run();
        zatvorit();
    }

    @FXML
    public void zrusit() {
        zatvorit();
    }

    private void zatvorit() {
        ((Stage) nazov_field.getScene().getWindow()).close();
    }
}
