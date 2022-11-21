package client;

import core.types.Flavor;
import core.types.Style;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the ny-style-view
 * that provides functionality for ordering ny style pizzas
 * @author Michael Liu, Genfu Liu
 */
public class NYStyleController extends PizzaStyleController {

    /**
     * Inherited method of the Initializable interface,
     * this is used to initialize our fields
     * @param url inherited param
     * @param resourceBundle inherited param
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        this.style = Style.NY;
    }

    /**
     * Custom implementation for changing the display images of the selected pizza
     * to corresponding NY style ones
     * @param flavor the selected pizza's flavor
     */
    @Override
    public void updateImageDisplay(Flavor flavor) {
        Image pizzaImage = null;

        switch (flavor) {
            case BBQ_Chicken:
                pizzaImage = new Image(getClass().getResource("images/ny_bbq_chicken.png").toExternalForm());
                break;
            case Deluxe:
                pizzaImage = new Image(getClass().getResource("images/ny_deluxe.png").toExternalForm());
                break;
            case Meatzza:
                pizzaImage = new Image(getClass().getResource("images/ny_meatzza.png").toExternalForm());
                break;
            case BuildYourOwn:
                pizzaImage = new Image(getClass().getResource("images/build_your_own.png").toExternalForm());
                break;
        }

        pizzaImageView.setImage(pizzaImage);
    }
}