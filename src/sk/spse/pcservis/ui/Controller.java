package sk.spse.pcservis.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.spse.pcservis.model.*;

import java.io.IOException;
import java.util.Map;

/**
 * Controller pre primary.fxml
 */
public class Controller {

    @FXML private Button pridat_komponent;

    @FXML private ComboBox<String> fitrovanie_skladu;
    @FXML private TableView<PCKomponent> inventar_table;
    @FXML private TableColumn<PCKomponent, String>  col_nazov;
    @FXML private TableColumn<PCKomponent, String>  col_kategoria;
    @FXML private TableColumn<PCKomponent, Double>  col_cena;

    @FXML private ComboBox<PCKomponent> CPU_ComboBox;
    @FXML private ComboBox<PCKomponent> RAM_ComboBox;
    @FXML private ComboBox<PCKomponent> GPU_ComboBox;
    @FXML private ComboBox<PCKomponent> SSD_ComboBox;
    @FXML private ComboBox<PCKomponent> MB_ComboBox;
    @FXML private ComboBox<PCKomponent> ZDROJ_ComboBox;
    @FXML private ComboBox<PCKomponent> CASE_ComboBox;

    @FXML private Label celkovaCenaLabel;

    @FXML private Button uloz_zostavu_btn;
    @FXML private Button predaj_zostavu_btn;

    @FXML private TextField generuj_TextField;
    @FXML private Button    generuj_btn;

    @FXML private TextArea  vypisZostavy;
    @FXML private TreeView<String> inventar_tree;

    private Sklad sklad;
    private PCServis pcServis;

    private PCZostava aktualnaZostava = new PCZostava();

    public void setSklad(Sklad sklad) {
        this.sklad = sklad;
        this.pcServis = new PCServis(sklad);
        initUI();
    }

    private void initUI() {
        col_nazov.setCellValueFactory(new PropertyValueFactory<>("nazov"));
        col_kategoria.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getKategoria().name()));
        col_cena.setCellValueFactory(new PropertyValueFactory<>("cena"));

        fitrovanie_skladu.getItems().add(0, "Všetky");
        fitrovanie_skladu.getSelectionModel().selectFirst();
        fitrovanie_skladu.setOnAction(e -> aktualizujInventarTable());

        nastavComboBox(CPU_ComboBox,   Kategoria.CPU);
        nastavComboBox(RAM_ComboBox,   Kategoria.RAM);
        nastavComboBox(GPU_ComboBox,   Kategoria.GPU);
        nastavComboBox(SSD_ComboBox,   Kategoria.SSD);
        nastavComboBox(MB_ComboBox,    Kategoria.MB);
        nastavComboBox(ZDROJ_ComboBox, Kategoria.ZDROJ);
        nastavComboBox(CASE_ComboBox,  Kategoria.CASE);

        CPU_ComboBox.setOnAction(e   -> prepocitajCenu());
        RAM_ComboBox.setOnAction(e   -> prepocitajCenu());
        GPU_ComboBox.setOnAction(e   -> prepocitajCenu());
        SSD_ComboBox.setOnAction(e   -> prepocitajCenu());
        MB_ComboBox.setOnAction(e    -> prepocitajCenu());
        ZDROJ_ComboBox.setOnAction(e -> prepocitajCenu());
        CASE_ComboBox.setOnAction(e  -> prepocitajCenu());

        aktualizujInventarTable();
        aktualizujInventarTree();
    }

    private void aktualizujInventarTable() {
        String filter = fitrovanie_skladu.getValue();
        ObservableList<PCKomponent> zoznam = FXCollections.observableArrayList();

        if (filter == null || filter.equals("Všetky")) {
            zoznam.addAll(sklad.getInventar().keySet());
        } else {
            Kategoria kat = Kategoria.valueOf(filter);
            zoznam.addAll(sklad.getKomponentyPodlaKategorie(kat).keySet());
        }

        inventar_table.setItems(zoznam);
    }

    private void aktualizujInventarTree() {
        TreeItem<String> root = new TreeItem<>("Komponenty");
        root.setExpanded(true);

        for (Kategoria kat : Kategoria.values()) {
            TreeItem<String> katItem = new TreeItem<>(kat.name());
            Map<PCKomponent, Integer> mapa = sklad.getKomponentyPodlaKategorie(kat);
            for (Map.Entry<PCKomponent, Integer> entry : mapa.entrySet()) {
                katItem.getChildren().add(
                        new TreeItem<>(entry.getKey().getNazov()
                                + " | " + entry.getValue() + " ks"
                                + " | " + entry.getKey().getCena() + " €"));
            }
            root.getChildren().add(katItem);
        }

        inventar_tree.setRoot(root);
    }

    private void nastavComboBox(ComboBox<PCKomponent> cb, Kategoria kat) {
        ObservableList<PCKomponent> items = FXCollections.observableArrayList(
                sklad.getKomponentyPodlaKategorie(kat).keySet());
        cb.setItems(items);
        cb.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(PCKomponent item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNazov() + " – " + item.getCena() + " €");
            }
        });
        cb.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(PCKomponent item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNazov() + " – " + item.getCena() + " €");
            }
        });
    }

    private void prepocitajCenu() {
        double suma = 0;
        ComboBox<?>[] boxy = {CPU_ComboBox, RAM_ComboBox, GPU_ComboBox,
                SSD_ComboBox, MB_ComboBox, ZDROJ_ComboBox, CASE_ComboBox};
        for (ComboBox<?> cb : boxy) {
            if (cb.getValue() instanceof PCKomponent k) suma += k.getCena();
        }
        celkovaCenaLabel.setText(String.format("%.2f €", suma));
    }

    @FXML
    public void ulozZostavu() {
        aktualnaZostava = new PCZostava();
        ComboBox<PCKomponent>[] boxy = new ComboBox[]{
                CPU_ComboBox, RAM_ComboBox, GPU_ComboBox,
                SSD_ComboBox, MB_ComboBox, ZDROJ_ComboBox, CASE_ComboBox};

        for (ComboBox<PCKomponent> cb : boxy) {
            if (cb.getValue() != null) aktualnaZostava.pridajKomponent(cb.getValue());
        }

        if (aktualnaZostava.getKomponenty().isEmpty()) {
            vypisZostavy.setText("Zostava je prázdna – vyberte aspoň jeden komponent.");
            return;
        }
        zobrazZostavu(aktualnaZostava);
    }

    @FXML
    public void predajZostavu() {
        if (aktualnaZostava.getKomponenty().isEmpty()) {
            vypisZostavy.setText("Nie je čo predať – zostava je prázdna.");
            return;
        }
        pcServis.predajZostavu(aktualnaZostava);
        aktualnaZostava = new PCZostava();

        CPU_ComboBox.setValue(null);
        RAM_ComboBox.setValue(null);
        GPU_ComboBox.setValue(null);
        SSD_ComboBox.setValue(null);
        MB_ComboBox.setValue(null);
        ZDROJ_ComboBox.setValue(null);
        CASE_ComboBox.setValue(null);
        celkovaCenaLabel.setText("0 €");

        vypisZostavy.setText("Zostava bola predaná a komponenty odobraté zo skladu.");
        aktualizujVsetko();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Automatické generovanie
    // ─────────────────────────────────────────────────────────────────────────

    @FXML
    public void generuj() {
        try {
            double suma = Double.parseDouble(generuj_TextField.getText());
            PCZostava zostava = PCServis.vytvorZostavuAutomaticky(suma);

            if (zostava == null || zostava.getKomponenty().isEmpty()) {
                vypisZostavy.setText("Nepodarilo sa vygenerovať zostavu pre zadaný rozpočet.");
                return;
            }

            aktualnaZostava = zostava;

            for (PCKomponent k : zostava.getKomponenty()) {
                switch (k.getKategoria()) {
                    case CPU   -> CPU_ComboBox.setValue(k);
                    case RAM   -> RAM_ComboBox.setValue(k);
                    case GPU   -> GPU_ComboBox.setValue(k);
                    case SSD   -> SSD_ComboBox.setValue(k);
                    case MB    -> MB_ComboBox.setValue(k);
                    case ZDROJ -> ZDROJ_ComboBox.setValue(k);
                    case CASE  -> CASE_ComboBox.setValue(k);
                }
            }
            prepocitajCenu();
            zobrazZostavu(zostava);

        } catch (NumberFormatException ex) {
            vypisZostavy.setText("Chybný vstup – zadajte číselný rozpočet (napr. 1500).");
        } catch (Exception ex) {
            vypisZostavy.setText("Chyba pri generovaní: " + ex.getMessage());
        }
    }

    @FXML
    public void otvorPridatKomponent() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("add_component.fxml"));
            Parent root = loader.load();

            AddComponentController acc = loader.getController();
            acc.setSkladACallback(sklad, this::aktualizujVsetko);

            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Pridať komponent");
            dialog.setScene(new Scene(root));
            dialog.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void zobrazZostavu(PCZostava zostava) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PC ZOSTAVA ===\n\n");
        for (PCKomponent k : zostava.getKomponenty()) {
            sb.append(String.format("%-8s %s  –  %.2f €%n",
                    "[" + k.getKategoria() + "]", k.getNazov(), k.getCena()));
        }
        sb.append(String.format("%n%-8s %.2f €", "CELKOM:", zostava.getCelkovaCena()));
        vypisZostavy.setText(sb.toString());
    }

    public void aktualizujVsetko() {
        nastavComboBox(CPU_ComboBox,   Kategoria.CPU);
        nastavComboBox(RAM_ComboBox,   Kategoria.RAM);
        nastavComboBox(GPU_ComboBox,   Kategoria.GPU);
        nastavComboBox(SSD_ComboBox,   Kategoria.SSD);
        nastavComboBox(MB_ComboBox,    Kategoria.MB);
        nastavComboBox(ZDROJ_ComboBox, Kategoria.ZDROJ);
        nastavComboBox(CASE_ComboBox,  Kategoria.CASE);
        aktualizujInventarTable();
        aktualizujInventarTree();
    }
}
