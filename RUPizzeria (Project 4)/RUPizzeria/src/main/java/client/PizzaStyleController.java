package client;

import core.types.Flavor;
import core.types.Size;
import core.types.Style;
import core.types.Topping;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import managers.StoreManager;
import pizzafactory.Pizza;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * This class is a more generic abstract controller class
 * for different kinds of pizza style controllers.
 * Reduces code duplication for the two other controllers,
 * contains all fxml injection elements, and functionality ordering pizzas
 * @author Michael Liu, Genfu Liu
 */
public abstract class PizzaStyleController implements Initializable {

    /**
     * Dropdown list for choosing the pizza flavor
     */
    @FXML
    protected ComboBox<Flavor> selectFlavorDropdown;

    /**
     * Group of radio buttons for selecting the size of the pizza
     */
    @FXML
    protected RadioButton smallSizeButton, mediumSizeButton, largeSizeButton;

    /**
     * TextField to display the crust of the selected pizza
     */
    @FXML
    protected TextField crustTypeDisplay;

    /**
     * ImageView for displaying the image of the selected pizza
     */
    @FXML
    protected ImageView pizzaImageView;

    /**
     * List of Available toppings for the selected pizza which the user can choose to add
     */
    @FXML
    protected ListView<Topping> availableToppings;

    /**
     * List of already added toppings for the selected pizza
     */
    @FXML
    protected ListView<Topping> selectedToppings;

    /**
     * Buttons to add and remove toppings from the pizza
     */
    @FXML
    protected Button addToppingButton, removeToppingButton;

    /**
     * TextField to display the current pizza's price
     * updated when the pizza is changed (size/toppings)
     */
    @FXML
    protected TextField pizzaPriceDisplay;

    /**
     * Button to add the current pizza to the cart
     */
    @FXML
    protected Button addToCart;

    /**
     * The current pizza that the user is ordering
     */
    protected Pizza currentOrder;

    /**
     * StoreManager class declared here for convince
     */
    protected StoreManager storeManager;

    /**
     * The current pizza's style
     * which will be set when the child controller is created
     */
    protected Style style;

    /**
     * Inherited method of the Initializable interface,
     * this is used to initialize our fields as well as some FXML elements
     * @param url inherited param
     * @param resourceBundle inherited param
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeManager = StoreManager.getInstance();

        selectFlavorDropdown.getItems().addAll(Flavor.values());
    }

    /**
     * This method will be called when the user selects a pizza flavor
     * from the selectFlavorDropdown ComboBox in either view
     * Initializes the currentOrder with the pizza the user selected
     */
    public void onSelectFlavor() {
        Flavor selectFlavor = selectFlavorDropdown.getSelectionModel().getSelectedItem();

        if (selectFlavor == null)
            return;

        currentOrder = storeManager.selectPizza(style, selectFlavor, Size.SMALL);

        updateOrderDisplay(selectFlavor);
    }

    /**
     * Set the pizza's size to small,
     * called when the radio button small is selected
     */
    public void onSmallSizeButton() {
        if (currentOrder == null) {
            MainController.log("Please select your pizza style first!");
            return;
        }

        currentOrder.setSize(Size.SMALL);
        updatePriceDisplay();
    }

    /**
     * Set the pizza's size to medium,
     * called when the radio button medium is selected
     */
    public void onMediumSizeButton() {
        if (currentOrder == null) {
            MainController.log("Please select your pizza style first!");
            return;
        }

        currentOrder.setSize(Size.MEDIUM);
        updatePriceDisplay();
    }

    /**
     * Set the pizza's size to large,
     * called when the radio button large is selected
     */
    public void onLargeSizeButton() {
        if (currentOrder == null) {
            MainController.log("Please select your pizza style first!");
            return;
        }

        currentOrder.setSize(Size.LARGE);
        updatePriceDisplay();
    }

    /**
     * Adds the topping that the user selected to the pizza,
     * called when the add topping button is clicked
     */
    public void onAddTopping() {
        Topping selectedTopping = availableToppings.getSelectionModel().getSelectedItem();

        if (selectedTopping == null) {
            MainController.log("Please select a topping you wish to add.");
            return;
        }

        if (currentOrder.getToppings().size() >= 7) {
            MainController.log("You have added the maximum amount of toppings allowed");
            return;
        }

        selectedToppings.getItems().add(selectedTopping);
        availableToppings.getItems().remove(selectedTopping);
        currentOrder.add(selectedTopping);
        updatePriceDisplay();
    }

    /**
     * Removes the topping that the user selected from the pizza,
     * called when the remove topping button is clicked
     */
    public void onRemoveTopping() {
        Topping selectedTopping = selectedToppings.getSelectionModel().getSelectedItem();

        if (selectedTopping == null) {
            MainController.log("Please select a topping you wish to remove.");
            return;
        }

        selectedToppings.getItems().remove(selectedTopping);
        availableToppings.getItems().add(selectedTopping);
        currentOrder.remove(selectedTopping);
        updatePriceDisplay();
    }

    /**
     * Adds the current order to the cart,
     * called when the addToCart button is clicked
     */
    public void onAddToCart() {
        if (currentOrder == null) {
            MainController.log("Please select your order");
            return;
        }

        currentOrder.setSize(smallSizeButton.isSelected() ? Size.SMALL : mediumSizeButton.isSelected() ? Size.MEDIUM : Size.LARGE);
        storeManager.addToCart(currentOrder);

        MainController.log("Order added!");

        selectFlavorDropdown.getSelectionModel().clearSelection();
        clearOrderDisplay();
        currentOrder = null;
    }

    /**
     * Updates the ImageView display for the current pizza with the corresponding flavor
     * different images for different styles
     * @param flavor the selected pizza's flavor
     */
    protected abstract void updateImageDisplay(Flavor flavor);

    /**
     * Update all displayed info when the user selects a flavor
     * in the selectFlavorDropdown
     * @param flavor the selected flavor
     */
    private void updateOrderDisplay(Flavor flavor) {
        crustTypeDisplay.setText(currentOrder.getCrust().toString());
        updatePriceDisplay();

        selectedToppings.getItems().setAll(currentOrder.getToppings());
        availableToppings.getItems().setAll(Topping.values());

        availableToppings.setDisable(flavor != Flavor.BuildYourOwn);
        addToppingButton.setDisable(flavor != Flavor.BuildYourOwn);
        removeToppingButton.setDisable(flavor != Flavor.BuildYourOwn);

        smallSizeButton.setSelected(true);

        updateImageDisplay(flavor);
    }

    /**
     * Updates the price display for the current order
     */
    private void updatePriceDisplay() {
        pizzaPriceDisplay.setText(String.format("%.2f", currentOrder.price()));
    }

    /**
     * Clear all displayed info
     */
    private void clearOrderDisplay() {
        crustTypeDisplay.clear();
        pizzaPriceDisplay.clear();

        selectedToppings.getItems().clear();
        availableToppings.getItems().setAll(Topping.values());

        availableToppings.setDisable(true);
        addToppingButton.setDisable(true);
        removeToppingButton.setDisable(true);

        smallSizeButton.setSelected(true);
        pizzaImageView.setImage(null);
    }

}