package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main starting point of the JavaFX Program
 * that launches the main window
 * @author Michael Liu, Genfu Liu
 */
public class MainApplication extends Application {

    /**
     * Inherited method that starts this javafx application
     * @param stage the main window
     * @throws IOException if the main fxml is not found
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);

        scene.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());

        stage.setTitle("RU Pizzeria");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Main starting point of this java program
     * @param args any args passed into the program
     */
    public static void main(String[] args) {
        launch();
    }

}