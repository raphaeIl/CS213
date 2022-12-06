package edu.rutgers.rupizzeria.main.managers;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import edu.rutgers.rupizzeria.main.core.customizable.Order;
import edu.rutgers.rupizzeria.main.core.customizable.StoreOrder;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Style;
import edu.rutgers.rupizzeria.main.pizzafactory.ChicagoPizza;
import edu.rutgers.rupizzeria.main.pizzafactory.NYPizza;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.main.pizzafactory.PizzaFactory;
import edu.rutgers.rupizzeria.utils.Logger;

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
    private Order currentCart;

    private Pizza currentItem;

    private List<ActionListener<Pizza>> cartItemChangedListeners;
    /**
     * Single private constructor to initialize all the fields
     */
    private StoreManager() {
        orderHistory = new StoreOrder();
        currentCart = new Order();

        cartItemChangedListeners = new ArrayList<>();
    }

    /**
     * This is used to create a pizza with the user's choice of style, flavor and size
     * @param style the style of the pizza
     * @param flavor the flavor of the pizza
     * @param size the size of the pizza
     * @return the created pizza matching the user's choice
     */
    public void selectPizza(Style style, Flavor flavor, Size size) {
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
            case Build_Your_Own:
                order = pizzaFactory.createBuildYourOwn();
                break;
        }

        order.setStyle(style);
        order.setSize(size);

        currentItem = order;
    }

    /**
     * Adds an item to the current cart
     * @param item the pizza to be added
     */
    public void addToCart(Pizza item) {
        this.currentCart.add(item);

        if (!this.cartItemChangedListeners.isEmpty()) {
            for (ActionListener<Pizza> al: cartItemChangedListeners)
                al.onAction(item, false);
        }
    }

    public void addCurrentItemToCart() {
        this.addToCart(this.currentItem);
    }

    /**
     * Removes an item from the current cart
     * @param item the pizza to be removed
     */
    public void removeFromCart(Pizza item) {
        this.currentCart.remove(item);

        if (!this.cartItemChangedListeners.isEmpty()) {
            for (ActionListener<Pizza> al: cartItemChangedListeners)
                al.onAction(item, true);
        }
    }

    /**
     * Clear the entire shopping cart
     */
    public void clearCart() {
        for (Pizza pizza : currentCart.getAllItems())
            this.removeFromCart(pizza);
    }

    /**
     * Places the current order which adds it to the order history
     */
    public void placeOrder() {
        orderHistory.add(currentCart);

        currentCart = new Order(); // clear the current order after placing
    }

    /**
     * Cancels a order which removes it from the order history
     * @param orderId
     */
    public void cancelOrder(int orderId) {
        orderHistory.remove(orderId);

        this.currentCart = new Order();
    }

    public void addCartItemChangedListener(ActionListener<Pizza> onCartItemChanged) {
        this.cartItemChangedListeners.add(onCartItemChanged);
    }

    public Pizza getCurrentItem() {
        return currentItem;
    }

    /**
     * Getter for the current order
     * @return the current order
     */
    public Order getCurrentCart() {
        return currentCart;
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
        return currentCart.getTotalPrice();
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
