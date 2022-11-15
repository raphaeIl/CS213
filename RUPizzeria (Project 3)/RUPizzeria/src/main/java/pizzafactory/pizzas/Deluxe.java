package pizzafactory.pizzas;

import core.Crust;
import core.Size;
import core.Topping;
import pizzafactory.Pizza;

import java.util.ArrayList;

public class Deluxe extends Pizza {

    public Deluxe(Size size, Crust crust, ArrayList<Topping> toppings) {
        super(size, crust, toppings);
    }

    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        return false;
    }

    @Override
    public double price() {
        return 0;
    }
}
