package core;

import pizzafactory.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order implements Customizable {

    private final List<Pizza> currentOrder;

    private final int orderId;

    public Order() {
        currentOrder = new ArrayList<>();

        orderId = (int) UUID.randomUUID().getMostSignificantBits(); // there is a really really small chance that this generates a duplicate number
    }

    @Override
    public boolean add(Object obj) {
        return currentOrder.add((Pizza) obj);
    }

    @Override
    public boolean remove(Object obj) {
        return currentOrder.remove((Pizza) obj);
    }

    public boolean isEmpty() {
        return currentOrder.isEmpty();
    }

    public void clear() {
        currentOrder.clear();
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Pizza> getAllItems() {
        return currentOrder;
    }

    public double getTotalPrice() {
        double price = 0.0d;

        for (Pizza pizza: currentOrder)
            price += pizza.price();

        return price;
    }

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