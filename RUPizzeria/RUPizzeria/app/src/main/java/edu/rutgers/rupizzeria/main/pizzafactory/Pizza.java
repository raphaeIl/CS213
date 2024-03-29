package edu.rutgers.rupizzeria.main.pizzafactory;

import edu.rutgers.rupizzeria.main.core.customizable.Customizable;
import edu.rutgers.rupizzeria.main.core.types.Crust;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Style;
import edu.rutgers.rupizzeria.main.core.types.Topping;

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
     * The style of the pizza
     */
    private Style style;

    /**
     * The flavor of the pizza
     */
    private Flavor flavor;

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
     * @param style
     * @param flavor
     */
    public Pizza(Size size, Crust crust, ArrayList<Topping> toppings, Style style, Flavor flavor) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
        this.style = style;
        this.flavor = flavor;
    }

    /**
     * Inherited method from Customizable that adds a topping to this pizza
     * @param obj the topping to be added
     * @return if it was added successfully or not
     */
    @Override
    public boolean add(Object obj) {
        if (!this.getToppings().contains(obj))
            return this.getToppings().add((Topping) obj);

        return false;
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

    public void setStyle(Style style) {
        this.style = style;
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
     * Getter for the Style of the pizza
     * @return the style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Getter for the Flavor of the pizza
     * @return the flavor
     */
    public Flavor getFlavor() {
        return flavor;
    }

    /**
     * Overridden toString for display purposes
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s %s Pizza • %s Style", size, flavor, style);
    }
}