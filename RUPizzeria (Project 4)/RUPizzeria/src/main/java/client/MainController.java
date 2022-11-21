package client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the main-view
 * provide the options of choosing the pizza styles, checking the current order in the
 * shopping cart, and managing the store orders.
 * @author Michael Liu, Genfu Liu
 */
public class MainController {

    /**
     * This method will be called when the "Chicago Styles" button is clicked
     * switches to the Chicago Style window
     */
    public void onChicagoStyle() {
        switchScene("Order Chicago Style", "chicago-style-view.fxml");
    }

    /**
     * This method will be called when the "NY Styles" button is clicked
     * switches to the NY Style window
     */
    public void onNYStyle() {
        switchScene("Order NY Style", "ny-style-view.fxml");
    }

    /**
     * This method will be called when the "Store Orders" button is clicked
     * switches to the Store Orders window
     */
    public void onStoreOrders() {
        switchScene("Store Orders", "store-orders-view.fxml");
    }

    /**
     * This method will be called when the "View Current Cart" button is clicked
     * switches to the Current Cart window
     */
    public void onViewCurrentCart() {
        switchScene("Current Cart", "current-cart-view.fxml");
    }

    /**
     * This is used to switch to a different scene
     * by launching a different window (stage)
     * @param windowName the title of the window that will be set
     * @param fxmlPath the path of the fxml for corresponding scene
     */
    private void switchScene(String windowName, String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent otherRoot = null;

        try {
            otherRoot = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();

        stage.setTitle(windowName);
        stage.setScene(new Scene(otherRoot));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL); // force focus on current window until user closes it

        stage.show();
    }

    /**
     * Since this is the main client controller class,
     * it will also be responsible for logging messages to the alert box "console"
     * such as helpful info as well as errors/warnings,
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
