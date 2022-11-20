package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import managers.StoreManager;
import pizzafactory.Pizza;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentCartController implements Initializable {

    @FXML
    private TextField orderNumberText;

    @FXML
    private ListView<Pizza> ordersList;

    @FXML
    private TextField subtotalText;

    @FXML
    private TextField salesTaxText;

    @FXML
    private TextField orderTotalText;

    private StoreManager storeManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeManager = StoreManager.getInstance();

        orderNumberText.setText(storeManager.getCurrentOrder().getOrderId() + "");
        ordersList.getItems().setAll(storeManager.getCurrentOrder().getAllItems());

        updatePriceDisplays();
    }

    public void updatePriceDisplays() {
        subtotalText.setText(String.format("%.2f", storeManager.getSubtotal()));
        salesTaxText.setText(String.format("%.2f", storeManager.getSalesTax()));
        orderTotalText.setText(String.format("%.2f", storeManager.getTotal()));
    }

    public void onPlaceOrder() {
        if (storeManager.getCurrentOrder().isEmpty()) {
            MainController.log("Order is empty!");
            return;
        }

        storeManager.placeOrder();

        orderNumberText.setText(storeManager.getCurrentOrder().getOrderId() + "");
        ordersList.getItems().setAll(storeManager.getCurrentOrder().getAllItems());

        updatePriceDisplays();
    }

    public void onRemovePizza() {
        Pizza pizzaToRemove = ordersList.getSelectionModel().getSelectedItem();

        storeManager.removeFromCart(pizzaToRemove);
        ordersList.getItems().remove(pizzaToRemove);

        updatePriceDisplays();
    }

    public void onClearCart() {
        storeManager.clearCart();
        ordersList.getItems().clear();

        updatePriceDisplays();
    }
}
