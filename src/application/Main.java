package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The Main class is where the application starts and is where the stage and scene are set up.
 *
 * @author Leonard Marshall Afzal
 * @version 26/06/2021
 */
public class Main extends Application {
    /**
     * This method is where the program starts and sets up the GUI.
     * @param stage where all the elements of GUI are implemented.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CurrencyConverterGUI.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Currency converter");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
