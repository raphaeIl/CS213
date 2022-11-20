package pizzafactory.flavors;

import core.Crust;
import core.Size;
import core.Topping;
import pizzafactory.Pizza;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza {

    public BuildYourOwn(Size size, Crust crust, ArrayList<Topping> toppings) {
        super(size, crust, toppings);
    }

    @Override
    public boolean add(Object obj) {
        return this.toppings.add((Topping) obj);
    }

    @Override
    public boolean remove(Object obj) {
        return this.toppings.remove((Topping) obj);
    }

    @Override
    public double price() {
        double price = 0.0;

        switch (this.size) {
            case SMALL:
                price = 8.99;
                break;
            case MEDIUM:
                price = 10.99;
                break;
            case LARGE:
                price = 12.99;
                break;
        }

        price += this.toppings.size() * 1.59;

        return price;
    }

    @Override
    public String toString() {
        return String.format("%s %s Custom Pizza with toppings: %s", size, crust, toppings);
    }
}
