package pizzafactory;

import core.Crust;
import core.Customizable;
import core.Size;
import core.Topping;

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

}