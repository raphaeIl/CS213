package managers;

import core.customizable.Order;
import core.customizable.StoreOrder;
import core.types.Flavor;
import core.types.Size;
import core.types.Style;
import pizzafactory.ChicagoPizza;
import pizzafactory.NYPizza;
import pizzafactory.Pizza;
import pizzafactory.PizzaFactory;

/**
 * This singleton class stores and manages all the orders in this restaurant,
 * as well as the database for storing order history
 * @author Michael Liu, Genfu Liu
 */
public class StoreManager {

    // region Singleton
    /**
     * This class implements the Singleton pattern for easier access of this class
     * throughout the entire program as well as limiting multiple instance of it to be
     * created since this application will most likely only need 1 store manager.
     */
    private static StoreManager instance = null;

    /**
     * Static method to get the single instance of this class
     * @return the single instance of this class
     */
    public static StoreManager getInstance() {
        if (instance == null)
            instance = new StoreManager();

        return instance;
    }
    // endregion

    /**
     * Instance of the StoreOrder class that stores the order history of the restaurant
     */
    private final StoreOrder orderHistory;

    /**
     * The current order that the user is about to place
     */
    private Order currentOrder;

    /**
     * Single private constructor to initialize all the fields
     */
    private StoreManager() {
        orderHistory = new StoreOrder();
        currentOrder = new Order();
    }

    /**
     * This is used to create a pizza with the user's choice of style, flavor and size
     * @param style the style of the pizza
     * @param flavor the flavor of the pizza
     * @param size the size of the pizza
     * @return the created pizza matching the user's choice
     */
    public Pizza selectPizza(Style style, Flavor flavor, Size size) {
        PizzaFactory pizzaFactory = null;
        Pizza order = null;

        switch (style) {
            case Chicago:
                pizzaFactory = new ChicagoPizza();
                break;
            case NY:
                pizzaFactory = new NYPizza();
                break;
        }

        switch (flavor) {
            case BBQ_Chicken:
                order = pizzaFactory.createBBQChicken();
                break;
            case Deluxe:
                order = pizzaFactory.createDeluxe();
                break;
            case Meatzza:
                order = pizzaFactory.createMeatzza();
                break;
            case BuildYourOwn:
                order = pizzaFactory.createBuildYourOwn();
                break;
        }

        order.setSize(size);

        return order;
    }

    /**
     * Adds an item to the current cart
     * @param order the pizza to be added
     */
    public void addToCart(Pizza order) {
        this.currentOrder.add(order);
    }

    /**
     * Removes an item from the current card
     * @param order the pizza to be removed
     */
    public void removeFromCart(Pizza order) {
        this.currentOrder.remove(order);
    }

    /**
     * Clear the entire shopping cart
     */
    public void clearCart() {
        this.currentOrder.clear();
    }

    /**
     * Places the current order which adds it to the order history
     */
    public void placeOrder() {
        orderHistory.add(currentOrder);

        currentOrder = new Order(); // clear the current order after placing
    }

    /**
     * Cancels a order which removes it from the order history
     * @param orderId
     */
    public void cancelOrder(int orderId) {
        orderHistory.remove(orderId);

        this.currentOrder = new Order();
    }

    /**
     * Getter for the current order
     * @return the current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Getter for the entire order history
     * @return a instanceo of the StoreOrder class representing the order history
     */
    public StoreOrder getOrderHistory() {
        return orderHistory;
    }

    /**
     * Gets the subtotal for the current order
     * @return the cost in a double
     */
    public double getSubtotal() {
        return currentOrder.getTotalPrice();
    }

    /**
     * Calculates the sales tax for this order
     * @return the sales tax in a double
     */
    public double getSalesTax() {
        final double TAX_RATE = 0.06625d;

        return getSubtotal() * TAX_RATE;
    }

    /**
     * Gets the total cost of this order including tax
     * @return the total cost in a double
     */
    public double getTotal() {
        return getSubtotal() + getSalesTax();
    }
}
