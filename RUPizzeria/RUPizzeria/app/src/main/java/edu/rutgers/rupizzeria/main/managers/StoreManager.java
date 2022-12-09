package edu.rutgers.rupizzeria.main.managers;

import java.util.ArrayList;
import java.util.List;

import edu.rutgers.rupizzeria.main.core.customizable.Order;
import edu.rutgers.rupizzeria.main.core.customizable.StoreOrder;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Style;
import edu.rutgers.rupizzeria.main.core.types.Topping;
import edu.rutgers.rupizzeria.main.pizzafactory.ChicagoPizza;
import edu.rutgers.rupizzeria.main.pizzafactory.NYPizza;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.main.pizzafactory.PizzaFactory;

/**
 * This singleton class stores and manages all the orders in this restaurant,
 * as well as the database for storing order history
 * Also implements the Observer Pattern where this class is the Observable/Subject class
 * and classes that implements ActionListener are the Observers/Subscribers
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

    /**
     * The current item/pizza the user is configuring
     */
    private Pizza currentItem;

    /**
     * A list of all Observers/Subscribers of this class which
     * all will be notified when any property of the current item Pizza is modified
     */
    private List<ActionListener<Pizza>> currentItemChangedListeners;

    /**
     * A list of all Observers/Subscribers of this class which
     * all will be notified when the cart is modified (add/remove/clear operations)
     */
    private List<ActionListener<Pizza>> cartChangedListeners;

    /**
     * Single private constructor to initialize all the fields
     */
    private StoreManager() {
        orderHistory = new StoreOrder();
        currentCart = new Order();

        currentItemChangedListeners = new ArrayList<>();
        cartChangedListeners = new ArrayList<>();
    }

    /**
     * This is used to create a pizza with the user's choice of style, flavor and size
     * @param style the style of the pizza
     * @param flavor the flavor of the pizza
     * @param size the size of the pizza
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
     * Adds the current item to the cart,
     * used when the user finishes configuring their current item
     * and decides to adds it to the cart
     */
    public void addCurrentItemToCart() {
        this.addToCart(this.currentItem);
    }

    /**
     * Adds an item to the current cart
     * and notifies the subscribers
     * @param item the pizza to be added
     */
    public void addToCart(Pizza item) {
        this.currentCart.add(item);

        notifyCartChanged(item, false);
    }

    /**
     * Removes an item from the current cart
     * @param item the pizza to be removed
     */
    public void removeFromCart(Pizza item) {
        this.currentCart.remove(item);

        notifyCartChanged(item, true);
    }

    /**
     * Clear the entire shopping cart
     */
    public void clearCart() {
        for (Pizza item: new ArrayList<>(currentCart.getAllItems()))
            this.removeFromCart(item);
    }

    /**
     * Places the current order which adds it to the order history
     * and resets the current order
     */
    public void placeOrder() {
        orderHistory.add(currentCart);

        currentCart = new Order(); // reset the current order after placing
    }

    /**
     * Cancels a order which removes it from the order history
     * @param orderId the order id to be removed
     */
    public void cancelOrder(int orderId) {
        orderHistory.remove(orderId);

        this.currentCart = new Order();
    }

    /**
     * Set the size of the current item and notifies the subscribers
     * @param size the size of the current item to be set
     */
    public void setCurrentItemSize(Size size) {
        currentItem.setSize(size);

        notifyCurrentItemChanged(currentItem);
    }

    /**
     * Adds a topping to the current item and notifies the subscribers
     * @param topping the topping to be added
     * @return if it was added successfully or not
     */
    public boolean addToppingToCurrentItem(Topping topping) {
        boolean result = currentItem.add(topping);

        notifyCurrentItemChanged(currentItem);
        return result;
    }

    /**
     * Removes a topping to the current item and notifies the subscribers
     * @param topping the topping to be removed
     * @return if it was removed successfully or not
     */
    public boolean removeToppingFromCurrentItem(Topping topping) {
        boolean result = currentItem.remove(topping);

        notifyCurrentItemChanged(currentItem);
        return result;
    }

    /**
     * Adds an ActionListener subscriber to the event listener who
     * will be notified when the current item is modified
     * @param onCurrentItemChanged the subscriber class
     */
    public void addCurrentItemChangedListener(ActionListener<Pizza> onCurrentItemChanged) {
        this.currentItemChangedListeners.add(onCurrentItemChanged);
    }

    /**
     * Adds an ActionListener subscriber to the event listener who
     * will be notified when the cart is changed
     * @param onCartItemChanged the subscriber class
     */
    public void addCartChangedListener(ActionListener<Pizza> onCartItemChanged) {
        this.cartChangedListeners.add(onCartItemChanged);
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
        return currentCart.getSalesTax();
    }

    /**
     * Gets the total cost of this order including tax
     * @return the total cost in a double
     */
    public double getTotal() {
        return getSubtotal() + getSalesTax();
    }

    /**
     * Getter for the current item
     * @return the current item
     */
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
     * Notifies all cartChangedListener Subscribers when the cart is changed
     * @param itemChanged the item in the cart that was changed
     * @param isRemoved if the item was removed from the cart or added
     */
    private void notifyCartChanged(Pizza itemChanged, boolean isRemoved) {
        if (!this.cartChangedListeners.isEmpty()) {
            for (ActionListener<Pizza> al: cartChangedListeners)
                al.onAction(itemChanged, isRemoved);
        }
    }

    /**
     * Notifies all currentItemChangedListener Subscribers when the current item is changed
     * @param itemChanged the the after it was changed
     */
    private void notifyCurrentItemChanged(Pizza itemChanged) {
        if (!this.currentItemChangedListeners.isEmpty()) {
            for (ActionListener<Pizza> al: currentItemChangedListeners)
                al.onAction(itemChanged, false);
        }
    }
}
