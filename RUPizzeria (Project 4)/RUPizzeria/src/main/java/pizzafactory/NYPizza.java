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
 * creates different types of NY Style Pizzas
 * @author Michael Liu, Genfu Liu
 */
public class NYPizza implements PizzaFactory {

    /**
     * Overridden method that creates a pizza that is NY style Deluxe flavor
     * @return the pizza created
     */
    @Override
    public Pizza createDeluxe() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Green_Pepper, Topping.Onion, Topping.Mushroom));

        return new Deluxe(Size.SMALL, Crust.Brooklyn, toppings);
    }

    /**
     * Overridden method that creates a pizza that is NY style Meatzza flavor
     * @return the pizza created
     */
    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Beef, Topping.Ham));

        return new Meatzza(Size.SMALL, Crust.Hand_tossed, toppings);
    }

    /**
     * Overridden method that creates a pizza that is NY style BBQChicken flavor
     * @return the pizza created
     */
    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.BBQ_Chicken, Topping.Green_Pepper, Topping.Provolone, Topping.Cheddar));

        return new BBQChicken(Size.SMALL, Crust.Thin, toppings);
    }

    /**
     * Overridden method that creates a pizza that is NY style Custom flavor
     * @return the pizza created
     */
    @Override
    public Pizza createBuildYourOwn() {
        ArrayList<Topping> toppings = new ArrayList<>();

        return new BuildYourOwn(Size.SMALL, Crust.Hand_tossed, toppings);
    }
}