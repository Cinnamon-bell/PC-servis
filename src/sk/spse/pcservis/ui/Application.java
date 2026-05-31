package sk.spse.pcservis.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import sk.spse.pcservis.model.Sklad;
import sk.spse.pcservis.ui.SkladProvider;

///
/// Trieda pre deklaratívne vytvorené GUI
///
/// Upravujte hlavne FXML súbor a Controller, nie túto triedu!
///

public class Application extends javafx.application.Application {


    // previously used sharedSklad; now use SkladProvider to avoid name collisions with javafx Application

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("primary.fxml"));

        Parent root = fxmlLoader.load();

        // Pokúsime sa odovzdať pripravený sklad controlleru (ak má setter)
        Object controller = fxmlLoader.getController();
        if (controller instanceof Controller) {
            ((Controller) controller).setSklad(SkladProvider.getSklad());
        }

        Scene scene = new Scene(root);

        stage.setTitle("Declarative Application 1");
        stage.setScene(scene);
        stage.show();
    }
}
