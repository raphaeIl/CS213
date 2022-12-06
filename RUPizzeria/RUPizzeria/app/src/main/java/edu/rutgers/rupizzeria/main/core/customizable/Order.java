package edu.rutgers.rupizzeria.main.core.customizable;

import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class represents a restaurant Order
 * contains a list of all items that is in the order (pizzas)
 * @author Michael Liu, Genfu Liu
 */
public class Order implements Customizable {

    /**
     * The list of pizzas that is in the order
     */
    private final List<Pizza> currentOrder;

    /**
     * A unique serial number for this order
     */
    private final int orderId;

    /**
     * Constructor to initialize the fields
     * as well as generating the serial number
     */
    public Order() {
        currentOrder = new ArrayList<>();

        orderId = (int) UUID.randomUUID().getMostSignificantBits(); // there is a really really small chance that this generates a duplicate number
    }

    /**
     * Inherited method from Customizable that adds an item to this order
     * @param obj the item to be added
     * @return if it was added successfully or not
     */
    @Override
    public boolean add(Object obj) {
        return currentOrder.add((Pizza) obj);
    }

    /**
     * Inherited method from Customizable that removes an item from this order
     * @param obj the item to be removed
     * @return if it was removed successfully or not
     */
    @Override
    public boolean remove(Object obj) {
        return currentOrder.remove((Pizza) obj);
    }

    /**
     * Checks to see if this order is currently empty
     * @return if the order is empty or not
     */
    public boolean isEmpty() {
        return currentOrder.isEmpty();
    }

    /**
     * Clears all items from this order
     */
    public void clear() {
        currentOrder.clear();
    }

    /**
     * Getter for the unique order id
     * @return the order id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Getter for all the items in the order
     * @return a List of Pizza items from this order
     */
    public List<Pizza> getAllItems() {
        return currentOrder;
    }

    /**
     * Gets the total price of this order
     * @return the total price
     */
    public double getTotalPrice() {
        double price = 0.0d;

        for (Pizza pizza: currentOrder)
            price += pizza.price();

        return price;
    }

    /**
     * Overriden toString method for displaying the order
     * either on javafx element or in the console for testing
     * @return the formatted string
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Order "  + orderId +":\n");

        for (Pizza pizza: currentOrder) {
            sb.append("\t\t");
            sb.append(pizza);
            sb.append("\n");
        }

        return sb.toString();
    }
}