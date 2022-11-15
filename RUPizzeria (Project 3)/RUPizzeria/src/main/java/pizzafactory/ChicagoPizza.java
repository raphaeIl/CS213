package pizzafactory;

import core.Crust;
import core.Size;
import core.Topping;
import pizzafactory.pizzas.Deluxe;

import java.util.ArrayList;
import java.util.List;

public class ChicagoPizza implements PizzaFactory {

    @Override
    public Pizza createDeluxe() {
        Size size = Size.MEDIUM;
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Green_Pepper, Topping.Onion, Topping.Mushroom));

        return new Deluxe(size, Crust.Deep_Dish, toppings);
    }

    @Override
    public Pizza createMeatzza() {
        return null;
    }

    @Override
    public Pizza createBBQChicken() {
        return null;
    }

    @Override
    public Pizza createBuildYourOwn() {
        return null;
    }
}