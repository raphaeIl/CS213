package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public void onChicagoStyle(ActionEvent event) {
        switchScene(event, "chicago-style-view.fxml");
    }

    public void onNYStyle(ActionEvent event) {
        switchScene(event, "ny-style-view.fxml");
    }

    public void onStoreOrders(ActionEvent event) {
        switchScene(event, "store-orders-view.fxml");
    }

    public void onCurrentOrder(ActionEvent event) {
        switchScene(event, "current-cart-view.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource(fxmlPath));
        Parent otherRoot = null;

        try {
            otherRoot = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(otherRoot));
        stage.show();
    }

    /**
     * Since this is the main client controller class,
     * it will also be responsible for logging messages to our console
     * such as helpful info as well as errors/warnings,
     * these can also be toggled with a boolean variable like boolean debug = true;
     * @param message the message to be logged
     */
    public static void log(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Log");
        alert.setHeaderText("Log Info");
        alert.setContentText(message);

        alert.show();
    }

    /**
     * Logs a message on the console with formatting
     * @param format a format string of the message to be logged
     * @param args arguments for the format string
     */
    public static void logf(String format, Object ... args) {
        log(String.format(format, args));
    }

}
