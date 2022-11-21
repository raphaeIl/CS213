package pizzafactory;

import core.types.Crust;
import core.customizable.Customizable;
import core.types.Size;
import core.types.Topping;

import java.util.ArrayList;

public abstract class Pizza implements Customizable {

    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    public abstract double price();

    public Pizza(Size size, Crust crust, ArrayList<Topping> toppings) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public Crust getCrust() {
        return crust;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return String.format("%s %s Pizza with toppings: %s", size, crust, toppings);
    }
}