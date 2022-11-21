package client;

import core.types.Flavor;
import core.types.Style;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class NYStyleController extends PizzaStyleController {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        this.style = Style.NY;
    }

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