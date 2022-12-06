package edu.rutgers.rupizzeria.main.pizzafactory;

import edu.rutgers.rupizzeria.main.core.types.Crust;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Style;
import edu.rutgers.rupizzeria.main.core.types.Topping;
import edu.rutgers.rupizzeria.main.pizzafactory.flavors.BBQChicken;
import edu.rutgers.rupizzeria.main.pizzafactory.flavors.BuildYourOwn;
import edu.rutgers.rupizzeria.main.pizzafactory.flavors.Deluxe;
import edu.rutgers.rupizzeria.main.pizzafactory.flavors.Meatzza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Implementation for a PizzaFactory,
 * creates different core.types of NY Style Pizzas
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
                Arrays.asList(Topping.Sausage, Topping.Pepperoni, Topping.Green_Pepper, Topping.Onion, Topping.Mushroom));

        return new Deluxe(Size.SMALL, Crust.Brooklyn, toppings, Style.Chicago, Flavor.Deluxe);
    }

    /**
     * Overridden method that creates a pizza that is NY style Meatzza flavor
     * @return the pizza created
     */
    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> toppings = new ArrayList<>(
                Arrays.asList(Topping.Sausage, Topping.Pepperoni, Topping.Beef, Topping.Ham));

        return new Meatzza(Size.SMALL, Crust.Hand_tossed, toppings, Style.Chicago, Flavor.Meatzza);
    }

    /**
     * Overridden method that creates a pizza that is NY style BBQChicken flavor
     * @return the pizza created
     */
    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> toppings = new ArrayList<>(
                Arrays.asList(Topping.BBQ_Chicken, Topping.Green_Pepper, Topping.Provolone, Topping.Cheddar));

        return new BBQChicken(Size.SMALL, Crust.Thin, toppings, Style.Chicago, Flavor.BBQ_Chicken);
    }

    /**
     * Overridden method that creates a pizza that is NY style Custom flavor
     * @return the pizza created
     */
    @Override
    public Pizza createBuildYourOwn() {
        ArrayList<Topping> toppings = new ArrayList<>();

        return new BuildYourOwn(Size.SMALL, Crust.Hand_tossed, toppings, Style.Chicago, Flavor.Build_Your_Own);
    }
}