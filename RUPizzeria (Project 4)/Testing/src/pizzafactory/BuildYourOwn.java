package pizzafactory;

import core.Crust;
import core.Size;
import core.Topping;
import pizzafactory.Pizza;

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
    public BuildYourOwn(Size size, Crust crust, ArrayList<Topping> toppings) {
        super(size, crust, toppings);
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

    /**
     * Overridden toString for display purposes
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s %s Custom Pizza with toppings: %s", getSize(), getCrust(), getToppings());
    }
}
