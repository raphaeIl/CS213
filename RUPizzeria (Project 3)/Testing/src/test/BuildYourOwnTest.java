package test;


import core.Crust;
import core.Size;
import core.Topping;
import org.junit.jupiter.api.Test;
import pizzafactory.BuildYourOwn;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Note: For some reasons for us JUunit just would not work with Maven or javafx,
 *
 */
class BuildYourOwnTest {

    @Test
    public void small_size_buildyourown_returns_correct_price() {
        ArrayList<Topping> toppings = new ArrayList<>();
        BuildYourOwn customPizza = new BuildYourOwn(Size.SMALL, Crust.Pan, toppings);
        final double SMALL_BUILDYOUROWN_PRICE = 8.99;

        customPizza.setSize(Size.SMALL);
        double expected_price = SMALL_BUILDYOUROWN_PRICE;
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

    @Test
    public void medium_size_buildyourown_returns_correct_price() {
        ArrayList<Topping> toppings = new ArrayList<>();
        BuildYourOwn customPizza = new BuildYourOwn(Size.MEDIUM, Crust.Pan, toppings);

        final double MEDIUM_BUILDYOUROWN_PRICE = 10.99;

        double expected_price = MEDIUM_BUILDYOUROWN_PRICE;
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

    @Test
    public void larger_size_buildyourown_returns_correct_price() {
        ArrayList<Topping> toppings = new ArrayList<>();
        BuildYourOwn customPizza = new BuildYourOwn(Size.LARGE, Crust.Pan, toppings);

        final double LARGE_BUILDYOUROWN_PRICE = 12.99;

        double expected_price = LARGE_BUILDYOUROWN_PRICE;
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

    @Test
    public void small_size_buildyourown_with_max_toppings_returns_correct_price() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Beef, Topping.Ham, Topping.Mushroom, Topping.Cheddar, Topping.Onion)
        );
        BuildYourOwn customPizza = new BuildYourOwn(Size.SMALL, Crust.Pan, toppings);

        final double SMALL_BUILDYOUROWN_PRICE = 8.99;
        final double EXTRA_FEE_PER_TOPPING = 1.59;

        double expected_price = SMALL_BUILDYOUROWN_PRICE + EXTRA_FEE_PER_TOPPING * toppings.size();
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

    @Test
    public void medium_size_buildyourown_with_max_toppings_returns_correct_price() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Beef, Topping.Ham, Topping.Mushroom, Topping.Cheddar, Topping.Onion)
        );
        BuildYourOwn customPizza = new BuildYourOwn(Size.MEDIUM, Crust.Pan, toppings);

        final double MEDIUM_BUILDYOUROWN_PRICE = 10.99;
        final double EXTRA_FEE_PER_TOPPING = 1.59;

        double expected_price = MEDIUM_BUILDYOUROWN_PRICE + EXTRA_FEE_PER_TOPPING * toppings.size();
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

    @Test
    public void large_size_buildyourown_with_max_toppings_returns_correct_price() {
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Sausage, Topping.Pepperoni, Topping.Beef, Topping.Ham, Topping.Mushroom, Topping.Cheddar, Topping.Onion)
        );
        BuildYourOwn customPizza = new BuildYourOwn(Size.LARGE, Crust.Pan, toppings);

        final double LARGE_BUILDYOUROWN_PRICE = 12.99;
        final double EXTRA_FEE_PER_TOPPING = 1.59;

        double expected_price = LARGE_BUILDYOUROWN_PRICE + EXTRA_FEE_PER_TOPPING * toppings.size();
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

}