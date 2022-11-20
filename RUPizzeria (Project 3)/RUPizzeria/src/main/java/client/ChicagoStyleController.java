package client;

import core.Style;

import java.net.URL;
import java.util.ResourceBundle;

public class ChicagoStyleController extends PizzaStyleController {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        this.style = Style.Chicago;
    }
}