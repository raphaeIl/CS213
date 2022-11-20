package pizzafactory;

import core.Crust;
import core.Customizable;
import core.Size;
import core.Topping;

import java.util.ArrayList;

public abstract class Pizza implements Customizable {

    protected ArrayList<Topping> toppings;
    protected Crust crust;
    protected Size size;

    public abstract double price();

    public Pizza(Size size, Crust crust, ArrayList<Topping> toppings) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public Crust getCrust() {
        return crust;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format("%s %s Pizza with toppings: %s", size, crust, toppings);
    }
}