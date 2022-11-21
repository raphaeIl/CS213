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

public class NYPizza implements PizzaFactory {

    @Override
    public Pizza createDeluxe() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Green_Pepper, Topping.Onion, Topping.Mushroom));

        return new Deluxe(Size.SMALL, Crust.Brooklyn, toppings);
    }

    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Beef, Topping.Ham));

        return new Meatzza(Size.SMALL, Crust.Hand_tossed, toppings);
    }

    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.BBQ_Chicken, Topping.Green_Pepper, Topping.Provolone, Topping.Cheddar));

        return new BBQChicken(Size.SMALL, Crust.Thin, toppings);
    }

    @Override
    public Pizza createBuildYourOwn() {
        ArrayList<Topping> toppings = new ArrayList<>();

        return new BuildYourOwn(Size.SMALL, Crust.Hand_tossed, toppings);
    }
}