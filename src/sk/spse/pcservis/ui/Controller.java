package sk.spse.pcservis.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Controller pre FXML súbor – obsahuje logiku aplikácie
 */
public class Controller {

    @FXML
    private TextArea textArea;

    @FXML
    private void ruka() {
        textArea.appendText("ruka pridana\n");
        textArea.setScrollTop(Double.MAX_VALUE);
    }

    @FXML
    private void noha() {
        textArea.appendText("noha pridana\n");
        textArea.setScrollTop(Double.MAX_VALUE);
    }

    @FXML
    private void hlava() {
        textArea.appendText("hlava pridana\n");
        textArea.setScrollTop(Double.MAX_VALUE);
    }

    @FXML
    private void trup() {
        textArea.appendText("trup pridana\n");
        textArea.setScrollTop(Double.MAX_VALUE);
    }

    @FXML
    private void poskladaj() {
        textArea.appendText("Montujem!!!\n");
        textArea.appendText("____________\n");
        textArea.appendText("ROBOT HOTOVÝ\n");
        textArea.appendText("____________\n");
        textArea.setScrollTop(Double.MAX_VALUE);
    }
}
