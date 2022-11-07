package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class GymManagerMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 600);

        GymManagerController controller = fxmlLoader.getController();

        controller.setConsole((TextArea) scene.lookup("#console"));
        scene.getStylesheets().add(GymManagerMain.class.getResource("styles.css").toExternalForm());

        stage.setResizable(false);
        stage.setTitle("Project 3 - Gym Manager");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}