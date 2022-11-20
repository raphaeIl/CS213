package client;

import core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import managers.StoreManager;
import pizzafactory.Pizza;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Getting rid of duplicate code
 */
public class PizzaStyleController implements Initializable {

    @FXML
    protected ComboBox<Flavor> selectFlavorDropdown;

    @FXML
    protected RadioButton smallSizeButton, mediumSizeButton, largeSizeButton;

    @FXML
    protected TextField crustTypeDisplay;

    @FXML
    protected ImageView pizzaImageView;

    @FXML
    protected ListView<Topping> availableToppings;

    @FXML
    protected ListView<Topping> selectedToppings;

    @FXML
    protected Button addToppingButton, removeToppingButton;

    @FXML
    protected TextField pizzaPriceDisplay;

    @FXML
    protected Button addToOrderButton;

    protected Pizza currentOrder;

    protected StoreManager storeManager;

    protected Style style;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeManager = StoreManager.getInstance();

        selectFlavorDropdown.getItems().addAll(Flavor.values());
    }

    public void selectFlavor(ActionEvent event) {
        Flavor selectFlavor = selectFlavorDropdown.getSelectionModel().getSelectedItem();

        if (selectFlavor == null)
            return;

        currentOrder = storeManager.selectPizza(style, selectFlavor, Size.SMALL);

        updateOrderDisplay(selectFlavor);
    }

    public void updateOrderDisplay(Flavor flavor) {
        crustTypeDisplay.setText(currentOrder.getCrust().toString());
        pizzaPriceDisplay.setText(currentOrder.price() + "");

        selectedToppings.getItems().setAll(currentOrder.getToppings());
        availableToppings.getItems().setAll(Topping.values());


        availableToppings.setDisable(flavor != Flavor.BuildYourOwn);
        addToppingButton.setDisable(flavor != Flavor.BuildYourOwn);
        removeToppingButton.setDisable(flavor != Flavor.BuildYourOwn);

        Image pizzaImage = null;

        switch (flavor) {
            case BBQ_Chicken:
                pizzaImage = new Image(getClass().getResource("images/NilouVeryPog.gif").toExternalForm());
                break;
            case Deluxe:
                pizzaImage = new Image(getClass().getResource("images/HutaoVeryPog.gif").toExternalForm());
                break;
            case Meatzza:
                pizzaImage = new Image(getClass().getResource("images/KeqingVeryPog.gif").toExternalForm());
                break;
            case BuildYourOwn:
                pizzaImage = new Image(getClass().getResource("images/KleeVeryPog.gif").toExternalForm());
                break;
        }

        pizzaImageView.setImage(pizzaImage);
    }

    public void onSmallSizeButton() {
        currentOrder.setSize(Size.SMALL);
        pizzaPriceDisplay.setText(currentOrder.price() + "");
    }

    public void onMediumSizeButton() {
        currentOrder.setSize(Size.MEDIUM);
        pizzaPriceDisplay.setText(currentOrder.price() + "");
    }

    public void onLargeSizeButton() {
        currentOrder.setSize(Size.LARGE);
        pizzaPriceDisplay.setText(currentOrder.price() + "");
    }


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
        pizzaPriceDisplay.setText(currentOrder.price() + "");
    }

    public void onRemoveTopping() {
        Topping selectedTopping = selectedToppings.getSelectionModel().getSelectedItem();

        if (selectedTopping == null) {
            MainController.log("Please select a topping you wish to remove.");
            return;
        }

        selectedToppings.getItems().remove(selectedTopping);
        availableToppings.getItems().add(selectedTopping);
        currentOrder.remove(selectedTopping);
        pizzaPriceDisplay.setText(currentOrder.price() + "");
    }

    public void onAddToOrder() {
        if (currentOrder == null) {
            MainController.log("Please select your order");
            return;
        }

        currentOrder.setSize(smallSizeButton.isSelected() ? Size.SMALL : mediumSizeButton.isSelected() ? Size.MEDIUM : Size.LARGE);
        storeManager.addToCart(currentOrder);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Log");
        alert.setHeaderText("Log Info");

        alert.setContentText("Order added!");
        alert.show();

        // reset view to default
        selectFlavorDropdown.getSelectionModel().clearSelection();
//        selectFlavorDropdown.setButtonCell(selectFlavorDropdown.getButtonCell());
        System.out.println(storeManager.getCurrentOrder());
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}