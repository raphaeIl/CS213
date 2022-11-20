package client;

import core.Style;

import java.net.URL;
import java.util.ResourceBundle;

public class NYStyleController extends PizzaStyleController {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        this.style = Style.NY;
    }
}