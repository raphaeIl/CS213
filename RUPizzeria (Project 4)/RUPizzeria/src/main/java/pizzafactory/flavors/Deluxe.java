package pizzafactory.flavors;

import core.types.Crust;
import core.types.Size;
import core.types.Topping;
import pizzafactory.Pizza;

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
     */
    public Deluxe(Size size, Crust crust, ArrayList<Topping> toppings) {
        super(size, crust, toppings);
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

    /**
     * Overridden toString for display purposes
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s %s Deluxe Pizza with toppings: %s", getSize(), getCrust(), getToppings());
    }
}
