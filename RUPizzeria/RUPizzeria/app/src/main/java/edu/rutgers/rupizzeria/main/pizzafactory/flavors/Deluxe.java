package edu.rutgers.rupizzeria.main.pizzafactory.flavors;

import edu.rutgers.rupizzeria.main.core.types.Crust;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Style;
import edu.rutgers.rupizzeria.main.core.types.Topping;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;

import java.util.ArrayList;

/**
 * Represents a pizza that is Deluxe flavor,
 * subclass of the Pizza class
 * @author Michael Liu, Genfu Liu
 */
public class Deluxe extends Pizza {

    /**
     * Inherited Constructor to initialize everything for the Deluxe pizza
     * @param size size of the pizza
     * @param crust crust of the pizza
     * @param toppings toppings that the pizza have
     * @param style
     * @param flavor
     */
    public Deluxe(Size size, Crust crust, ArrayList<Topping> toppings, Style style, Flavor flavor) {
        super(size, crust, toppings, style, flavor);
    }

    /**
     * Implementation of the method to calculate the price of the pizza
     * @return the price
     */
    @Override
    public double price() {
        switch (this.getSize()) {
            case SMALL:
                return 14.99;
            case MEDIUM:
                return 16.99;
            case LARGE:
                return 18.99;
            default:
                return -1;
        }
    }
}
