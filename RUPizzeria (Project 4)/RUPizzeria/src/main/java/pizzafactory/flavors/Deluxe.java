package pizzafactory.flavors;

import core.types.Crust;
import core.types.Size;
import core.types.Topping;
import pizzafactory.Pizza;

import java.util.ArrayList;

public class Deluxe extends Pizza {

    public Deluxe(Size size, Crust crust, ArrayList<Topping> toppings) {
        super(size, crust, toppings);
    }

    @Override
    public boolean add(Object obj) {
        return this.getToppings().add((Topping) obj);
    }

    @Override
    public boolean remove(Object obj) {
        return this.getToppings().remove((Topping) obj);
    }

    @Override
    public double price() {
        switch (this.getSize()) {
            case SMALL:
                return 14.99;
            case MEDIUM:
                return 16.99;
            case LARGE:
                return 18.99;
            default:
                return -1;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s Deluxe Pizza with toppings: %s", getSize(), getCrust(), getToppings());
    }
}
