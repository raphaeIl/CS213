package pizzafactory;

import core.types.Crust;
import core.types.Size;
import core.types.Topping;
import pizzafactory.flavors.BBQChicken;
import pizzafactory.flavors.BuildYourOwn;
import pizzafactory.flavors.Deluxe;
import pizzafactory.flavors.Meatzza;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for a PizzaFactory,
 * creates different core.types of Chicago Style Pizzas
 * @author Michael Liu, Genfu Liu
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Overridden method that creates a pizza that is Chicago style Deluxe flavor
     * @return the pizza created
     */
    @Override
    public Pizza createDeluxe() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Green_Pepper, Topping.Onion, Topping.Mushroom));

        return new Deluxe(Size.SMALL, Crust.Deep_Dish, toppings);
    }

    /**
     * Overridden method that creates a pizza that is Chicago style Meatzza flavor
     * @return the pizza created
     */
    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Beef, Topping.Ham));

        return new Meatzza(Size.SMALL, Crust.Stuffed, toppings);
    }

    /**
     * Overridden method that creates a pizza that is Chicago style BBQChicken flavor
     * @return the pizza created
     */
    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.BBQ_Chicken, Topping.Green_Pepper, Topping.Provolone, Topping.Cheddar));

        return new BBQChicken(Size.SMALL, Crust.Pan, toppings);
    }

    /**
     * Overridden method that creates a pizza that is Chicago style Custom flavor
     * @return the pizza created
     */
    @Override
    public Pizza createBuildYourOwn() {
        ArrayList<Topping> toppings = new ArrayList<>();

        return new BuildYourOwn(Size.SMALL, Crust.Pan, toppings);
    }
}