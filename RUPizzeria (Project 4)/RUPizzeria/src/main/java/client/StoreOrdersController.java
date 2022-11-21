package client;

import core.customizable.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import managers.StoreManager;
import pizzafactory.Pizza;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the store-orders-view
 * that provides functionality for managing orders placed by users
 * @author Michael Liu, Genfu Liu
 */
public class StoreOrdersController implements Initializable {

    /**
     * ComboBox Dropdown that allows the users to select which order they want to view
     */
    @FXML
    private ComboBox<Integer> selectOrderDropdown;

    /**
     * ListView to display the list of all items in that order
     */
    @FXML
    private ListView<Pizza> itemsList;

    /**
     * TextField for the total cost of the selected order
     */
    @FXML
    private TextField orderTotalText;

    /**
     * StoreManager class declared here for convince
     */
    private StoreManager storeManager;

    /**
     * Inherited method of the Initializable interface,
     * this is used to initialize our fields as well as some FXML elements
     * @param url inherited param
     * @param resourceBundle inherited param
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeManager = StoreManager.getInstance();

        selectOrderDropdown.getItems().setAll(storeManager.getOrderHistory().getAllOrders().keySet());
    }

    /**
     * Updates all display info when the user selects an order,
     * called when the users select an option from the selectOrderDropdown
     */
    public void onSelectOrderNumber() {
        Order selectedOrder = storeManager.getOrderHistory().getAllOrders().get(selectOrderDropdown.getSelectionModel().getSelectedItem());

        if (selectedOrder == null)
            return;

        itemsList.getItems().setAll(selectedOrder.getAllItems());
        orderTotalText.setText(String.format("%.2f", selectedOrder.getTotalPrice()));
    }

    /**
     * Cancels the selected order,
     * called when the "cancel order" button is clicked
     */
    public void onCancelOrder() {
        storeManager.cancelOrder(selectOrderDropdown.getSelectionModel().getSelectedItem());

        selectOrderDropdown.getSelectionModel().clearSelection();
        selectOrderDropdown.getItems().setAll(storeManager.getOrderHistory().getAllOrders().keySet());
        itemsList.getItems().clear();
        orderTotalText.clear();
    }

    /**
     * Exports the order history to an external txt file,
     * called when the "export store orders" button is clicked
     */
    public void onExportOrder() {
        String exportFile = "rupizzeria_order_history.txt";

        if (storeManager.getOrderHistory().export(exportFile)) {
            MainController.logf("Successfully exported order history to %s", exportFile);
        }
    }
}
