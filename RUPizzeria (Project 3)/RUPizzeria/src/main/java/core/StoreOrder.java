package core;

import client.MainController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreOrder implements Customizable {

    private final Map<Integer, Order> orderHistory;

    public StoreOrder() {
        orderHistory = new HashMap<>();
    }

    @Override
    public boolean add(Object obj) {
        Order orderToAdd = (Order) obj;
        orderHistory.put(orderToAdd.getOrderId(), orderToAdd);
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        orderHistory.remove(((Order)obj).getOrderId());

        return true;
    }

    public boolean remove(int orderId) {
        orderHistory.remove(orderId);

        return true;
    }

    public Map<Integer, Order> getAllOrders() {
        return orderHistory;
    }

    public boolean export(String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)){
            fileWriter.write(this.toString());
        } catch (IOException e) {
            MainController.logf("File path %s does not exist!", filePath);
            return false;
        }

        return true;
    }

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