package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import managers.StoreManager;
import pizzafactory.Pizza;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the current-cart-view
 * that provides functionality for managing and placing orders in the cart
 * @author Michael Liu, Genfu Liu
 */
public class CurrentCartController implements Initializable {

    /**
     * TextField to display the current order's id
     */
    @FXML
    private TextField orderNumberText;

    /**
     * ListView to display the list of items in the current order
     */
    @FXML
    private ListView<Pizza> ordersList;

    /**
     * TextField to display the subtotal price
     */
    @FXML
    private TextField subtotalText;

    /**
     * TextField to display the sales tax
     */
    @FXML
    private TextField salesTaxText;

    /**
     * TextField to display the total cost of this
     */
    @FXML
    private TextField orderTotalText;

    /**
     * StoreManager class used for convince
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

        orderNumberText.setText(storeManager.getCurrentOrder().getOrderId() + "");
        ordersList.getItems().setAll(storeManager.getCurrentOrder().getAllItems());

        updatePriceDisplays();
    }

    /**
     * Update the subtotal, salestax as well as the total cost displays
     * of the current order
     */
    public void updatePriceDisplays() {
        subtotalText.setText(String.format("%.2f", storeManager.getSubtotal()));
        salesTaxText.setText(String.format("%.2f", storeManager.getSalesTax()));
        orderTotalText.setText(String.format("%.2f", storeManager.getTotal()));
    }

    /**
     * Used for placing an order,
     * called when the place order button is clicked
     */
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

    /**
     * Removes a pizza from the current order,
     * called when the "Remove Pizza" button is clicked
     */
    public void onRemovePizza() {
        Pizza pizzaToRemove = ordersList.getSelectionModel().getSelectedItem();

        storeManager.removeFromCart(pizzaToRemove);
        ordersList.getItems().remove(pizzaToRemove);

        updatePriceDisplays();
    }

    /**
     * Clears all items from the current cart,
     * called when the clear cart button is clicked
     */
    public void onClearCart() {
        storeManager.clearCart();
        ordersList.getItems().clear();

        updatePriceDisplays();
    }
}
