package edu.rutgers.rupizzeria.main.pizzafactory.flavors;

import edu.rutgers.rupizzeria.main.core.types.Crust;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Style;
import edu.rutgers.rupizzeria.main.core.types.Topping;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import java.util.ArrayList;

/**
 * Represents a pizza that is Custom flavor,
 * subclass of the Pizza class
 * @author Michael Liu, Genfu Liu
 */
public class BuildYourOwn extends Pizza {

    /**
     * Inherited Constructor to initialize everything for the Custom pizza
     * @param size size of the pizza
     * @param crust crust of the pizza
     * @param toppings toppings that the pizza have
     */
    public BuildYourOwn(Size size, Crust crust, ArrayList<Topping> toppings, Style style, Flavor flavor) {
        super(size, crust, toppings, style, flavor);
    }

    /**
     * Implementation of the method to calculate the price of the pizza
     * @return the price
     */
    @Override
    public double price() {
        double price = 0.0;

        switch (this.getSize()) {
            case SMALL:
                price = 8.99;
                break;
            case MEDIUM:
                price = 10.99;
                break;
            case LARGE:
                price = 12.99;
                break;
        }

        price += this.getToppings().size() * 1.59;

        return price;
    }
}
