package client;

import core.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import managers.StoreManager;
import pizzafactory.Pizza;

import java.net.URL;
import java.util.ResourceBundle;

public class StoreOrdersController implements Initializable {

    @FXML
    private ComboBox<Integer> selectOrderDropdown;

    @FXML
    private ListView<Pizza> ordersList;

    @FXML
    private TextField orderTotalText;

    private StoreManager storeManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeManager = StoreManager.getInstance();

        selectOrderDropdown.getItems().setAll(storeManager.getOrderHistory().getAllOrders().keySet());
    }

    public void onSelectOrderNumber() {
        Order selectedOrder = storeManager.getOrderHistory().getAllOrders().get(selectOrderDropdown.getSelectionModel().getSelectedItem());

        if (selectedOrder == null)
            return;

        ordersList.getItems().setAll(selectedOrder.getAllItems());
        orderTotalText.setText(String.format("%.2f", selectedOrder.getTotalPrice()));
    }

    public void onCancelOrder() {
        storeManager.cancelOrder(selectOrderDropdown.getSelectionModel().getSelectedItem());

        selectOrderDropdown.getSelectionModel().clearSelection();
        selectOrderDropdown.getItems().setAll(storeManager.getOrderHistory().getAllOrders().keySet());
        ordersList.getItems().clear();
        orderTotalText.clear();
    }

    public void onExportOrder() {
        String exportFile = "rupizzeria_order_history.txt";

        if (storeManager.getOrderHistory().export(exportFile)) {
            MainController.logf("Successfully exported order history to %s", exportFile);
        }
    }
}
