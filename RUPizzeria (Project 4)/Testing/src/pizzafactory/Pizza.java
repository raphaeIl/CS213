package pizzafactory;

import core.Crust;
import core.Customizable;
import core.Size;
import core.Topping;

import java.util.ArrayList;

/**
 * Represents a pizza order, toppings can be added
 * @author Michael Liu, Genfu Liu
 */
public abstract class Pizza implements Customizable {

    /**
     * The toppings that the pizza currently have
     */
    private ArrayList<Topping> toppings;

    /**
     * The Crust of the pizza
     */
    private Crust crust;

    /**
     * The size of the pizza
     */
    private Size size;

    /**
     * Method to calculate the price of the pizza
     * which will be different depends on the type of the pizza
     * @return
     */
    public abstract double price();

    /**
     * Constructor to initialize everything for the pizza
     * @param size size of the pizza
     * @param crust crust of the pizza
     * @param toppings toppings that the pizza have
     */
    public Pizza(Size size, Crust crust, ArrayList<Topping> toppings) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    /**
     * Inherited method from Customizable that adds a topping to this pizza
     * @param obj the topping to be added
     * @return if it was added successfully or not
     */
    @Override
    public boolean add(Object obj) {
        return this.getToppings().add((Topping) obj);
    }

    /**
     * Inherited method from Customizable that removes a topping from this pizza
     * @param obj the topping to be removed
     * @return if it was removed successfully or not
     */
    @Override
    public boolean remove(Object obj) {
        return this.getToppings().remove((Topping) obj);
    }

    /**
     * Setter for the size of the pizza
     * @param size
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Getter for the size of the pizza
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Getter for the Crust of the pizza
     * @return the crust
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Getter for the toppings of the pizza
     * @return the toppings in a List
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Overridden toString for display purposes
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s %s Pizza with toppings: %s", size, crust, toppings);
    }
}