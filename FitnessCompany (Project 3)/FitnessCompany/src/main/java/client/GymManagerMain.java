package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

/*
    Just in case if my file paths are not working, we've placed a built version
    of this program in a separate folder called "build" in the zip,
    which contains all the javafx liberties and probably the correct file path.

    To run the executable jar file, launch run.bat (windows), or copy the command in
    run.txt into a command prompt (others?).
*/

/**
 * The main class to "launch" the GUI Client
 * @author Michael Liu, Genfu Liu
 */
public class GymManagerMain extends Application {

    /**
     * Start method of a javafx Application class
     * creates the actual javafx stage and scene
     * as well as loading all the resources
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 600);

        GymManagerController controller = fxmlLoader.getController();

        controller.setConsole((TextArea) scene.lookup("#console")); // setting our custom console
        scene.getStylesheets().add(GymManagerMain.class.getResource("styles.css").toExternalForm());

        stage.setResizable(false);
        stage.setTitle("Project 3 - Gym Manager");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * main method, starting point of the program
     * which launches the javafx application
     */
    public static void main(String[] args) {
        launch();
    }
}