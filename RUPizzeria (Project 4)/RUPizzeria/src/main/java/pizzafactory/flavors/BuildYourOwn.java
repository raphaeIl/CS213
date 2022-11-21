package pizzafactory.flavors;

import core.types.Crust;
import core.types.Size;
import core.types.Topping;
import pizzafactory.Pizza;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza {

    public BuildYourOwn(Size size, Crust crust, ArrayList<Topping> toppings) {
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
        double price = 0.0;

        switch (this.getSize()) {
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

        price += this.getToppings().size() * 1.59;

        return price;
    }

    @Override
    public String toString() {
        return String.format("%s %s Custom Pizza with toppings: %s", getSize(), getCrust(), getToppings());
    }
}
