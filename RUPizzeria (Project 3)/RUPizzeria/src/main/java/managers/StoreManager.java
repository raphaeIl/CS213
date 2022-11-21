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

public class StoreManager {

    // region Singleton
    /**
     * This class implements the Singleton pattern for easier access of this class
     * throughout the entire program as well as limiting multiple instance of it to be
     * created since this application will most likely only need 1 database manager.
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

    private final StoreOrder orderHistory;
    private Order currentOrder;

    private StoreManager() {
        orderHistory = new StoreOrder();
        currentOrder = new Order();
    }

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

    public void addToCart(Pizza order) {
        this.currentOrder.add(order);
    }

    public void removeFromCart(Pizza order) {
        this.currentOrder.remove(order);
    }

    public void clearCart() {
        this.currentOrder.clear();
    }

    public void cancelOrder(int orderId) {
        orderHistory.remove(orderId);

        this.currentOrder = new Order();
    }

    public void placeOrder() {
        orderHistory.add(currentOrder);

        currentOrder = new Order(); // clear the current order after placing
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public StoreOrder getOrderHistory() {
        return orderHistory;
    }

    public double getSubtotal() {
        return currentOrder.getTotalPrice();
    }

    public double getSalesTax() {
        return getSubtotal() * 0.06625d;
    }

    public double getTotal() {
        return getSubtotal() + getSalesTax();
    }
}
