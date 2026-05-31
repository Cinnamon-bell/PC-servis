package sk.spse.pcservis.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sk.spse.pcservis.model.PCServis;
import sk.spse.pcservis.model.Sklad;

/**
 * Controller pre FXML súbor – obsahuje logiku aplikácie ||getKomponentyPodlaKategorie
 */
public class Controller {

    @FXML
    private Button generuj_btn;
    @FXML
    private TextField generuj_TextField;

    @FXML
    private TextArea vypisZostavy;

    //@FXML
    //private TableView<inventar_table> inventar_table;

    // Button actions
    public void generuj(){
        try {
            double suma = Double.parseDouble(generuj_TextField.getText());
            Object zostava = PCServis.vytvorZostavuAutomaticky(suma);
            // Zobrazíme zostavu v TextArea; použijeme toString() ak to nie je string
            vypisZostavy.setText(zostava == null ? "" : String.valueOf(zostava));
        } catch (NumberFormatException ex) {
            vypisZostavy.setText("Chybný vstup pre rozpočet: prosím zadajte číslo.");
        } catch (Exception ex) {
            vypisZostavy.setText("Chyba pri generovaní zostavy: " + ex.getMessage());
        }
    }

    // Sklad pre UI - bude nastavený z Application prior launch
    private Sklad sklad;

    public void setSklad(Sklad sklad) {
        this.sklad = sklad;
        // TODO: inicializovať UI komponenty (TableView, ComboBoxy) podľa obsahu skladu
        // napr. naplniť inventar_table alebo ComboBoxy
    }
}
