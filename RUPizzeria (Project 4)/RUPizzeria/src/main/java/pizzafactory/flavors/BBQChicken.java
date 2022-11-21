package pizzafactory.flavors;

import core.types.Crust;
import core.types.Size;
import core.types.Topping;
import pizzafactory.Pizza;

import java.util.ArrayList;

/**
 * Represents a pizza that is BBQChicken flavor,
 * subclass of the Pizza class
 * @author Michael Liu, Genfu Liu
 */
public class BBQChicken extends Pizza {

    /**
     * Inherited Constructor to initialize everything for the BBQChicken pizza
     * @param size size of the pizza
     * @param crust crust of the pizza
     * @param toppings toppings that the pizza have
     */
    public BBQChicken(Size size, Crust crust, ArrayList<Topping> toppings) {
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
                return 13.99;
            case MEDIUM:
                return 15.99;
            case LARGE:
                return 17.99;
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
        return String.format("%s %s BBQ Chicken Pizza with toppings: %s", getSize(), getCrust(), getToppings());
    }
}
