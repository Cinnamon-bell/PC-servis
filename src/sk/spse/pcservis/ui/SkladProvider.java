package sk.spse.pcservis.ui;

import sk.spse.pcservis.model.Sklad;

/**
 * Simple holder to share a prepared Sklad instance between non-FX startup code and the JavaFX Application.
 */
public class SkladProvider {
    private static Sklad sklad;

    public static void setSklad(Sklad s) {
        sklad = s;
    }

    public static Sklad getSklad() {
        return sklad;
    }
}

