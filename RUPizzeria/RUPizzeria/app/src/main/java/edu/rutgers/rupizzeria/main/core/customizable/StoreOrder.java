package edu.rutgers.rupizzeria.main.core.customizable;

import edu.rutgers.rupizzeria.utils.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the order history of the restaurant
 * contains a database of all the past orders
 * @author Michael Liu, Genfu Liu
 */
public class StoreOrder implements Customizable {

    /**
     * A hashtable representing the entire order history,
     * keys are the order's unique id and values are the orders themselves
     * so that orders can be look-upped by their ids
     */
    private final Map<Integer, Order> orderHistory;

    /**
     * Constructor to initialize the hashtable
     */
    public StoreOrder() {
        orderHistory = new HashMap<>();
    }

    /**
     * Inherited method from Customizable that adds an order to the order history
     * @param obj the order to be added
     * @return if it was added successfully or not
     */
    @Override
    public boolean add(Object obj) {
        Order orderToAdd = (Order) obj;
        orderHistory.put(orderToAdd.getOrderId(), orderToAdd);
        return true;
    }

    /**
     * Inherited method from Customizable that removes an order from the order history
     * @param obj the order to be removed
     * @return if it was removed successfully or not
     */
    @Override
    public boolean remove(Object obj) {
        orderHistory.remove(((Order)obj).getOrderId());

        return true;
    }

    /**
     * Overloaded remove method for convenience,
     * removes an order from the order history by its ID
     * @param orderId the unique id of the order
     * @return if it was removed successfully or not
     */
    public boolean remove(int orderId) {
        orderHistory.remove(orderId);

        return true;
    }

    /**
     * Getter for the entire order history
     * @return a hashtable containing the keys and their corresponding order
     */
    public Map<Integer, Order> getAllOrders() {
        return orderHistory;
    }

    /**
     * This is used to save the store order history to an external text file
     * @param filePath the file path to save the order history to
     * @return if the order history was successfully saved to specified file or not
     */
    public boolean export(String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)){
            fileWriter.write(this.toString());
        } catch (IOException e) {
            Logger.logf("File path %s does not exist!", filePath);
            return false;
        }

        return true;
    }

    /**
     * Overriden toString method for displaying the orderhistory
     * either on javafx element or for when exporting it to a file
     * @return the formatted string
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("OrderHistory: \n");

        for (Order order: orderHistory.values()) {
            sb.append("\t");
            sb.append(order);
            sb.append("\n");
        }

        return sb.toString();
    }
}