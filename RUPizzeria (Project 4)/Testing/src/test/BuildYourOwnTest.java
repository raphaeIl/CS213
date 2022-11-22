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
 * JUnitTest class for the BuildYourOwn class (subclass of pizza)
 * TODO Note: For some reasons JUnit just would not work with Maven or javafx for us,
 * so we created a separate default intellij project in a folder "Testing" next to the main project folder
 * All the java files in there are copied over from this project
 * The tests run fine in there but in this project it would not work.
 * @author Michael Liu, Genfu Liu
 */
class BuildYourOwnTest {

    /**
     * Testing if a small buildyourown pizza with no toppings returns the correct price
     */
    @Test
    public void small_size_buildyourown_returns_correct_price() {
        // creating a buildyourown pizza with small size and no toppings
        ArrayList<Topping> toppings = new ArrayList<>();
        BuildYourOwn customPizza = new BuildYourOwn(Size.SMALL, Crust.Pan, toppings);

        final double SMALL_BUILDYOUROWN_PRICE = 8.99;  // correct price taken from project pdf

        double expected_price = SMALL_BUILDYOUROWN_PRICE;
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

    /**
     * Testing if a medium buildyourown pizza with no toppings returns the correct price
     */
    @Test
    public void medium_size_buildyourown_returns_correct_price() {
        // creating a buildyourown pizza with medium size and no toppings
        ArrayList<Topping> toppings = new ArrayList<>();
        BuildYourOwn customPizza = new BuildYourOwn(Size.MEDIUM, Crust.Pan, toppings);

        final double MEDIUM_BUILDYOUROWN_PRICE = 10.99;

        double expected_price = MEDIUM_BUILDYOUROWN_PRICE;
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

    /**
     * Testing if a large buildyourown pizza with no toppings returns the correct price
     */
    @Test
    public void larger_size_buildyourown_returns_correct_price() {
        // creating a buildyourown pizza with small large and no toppings
        ArrayList<Topping> toppings = new ArrayList<>();
        BuildYourOwn customPizza = new BuildYourOwn(Size.LARGE, Crust.Pan, toppings);

        final double LARGE_BUILDYOUROWN_PRICE = 12.99;

        double expected_price = LARGE_BUILDYOUROWN_PRICE;
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

    /**
     * Testing if a small buildyourown pizza with max toppings (7) returns the correct price
     */
    @Test
    public void small_size_buildyourown_with_max_toppings_returns_correct_price() {
        // creating a buildyourown pizza with small size and 7 toppings
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

    /**
     * Testing if a medium buildyourown pizza with 5 toppings returns the correct price
     */
    @Test
    public void medium_size_buildyourown_with_five_toppings_returns_correct_price() {
        // creating a buildyourown pizza with medium size and 5 toppings
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Beef, Topping.Ham, Topping.Mushroom, Topping.Cheddar, Topping.Onion)
        );
        BuildYourOwn customPizza = new BuildYourOwn(Size.MEDIUM, Crust.Pan, toppings);

        final double MEDIUM_BUILDYOUROWN_PRICE = 10.99;
        final double EXTRA_FEE_PER_TOPPING = 1.59;

        double expected_price = MEDIUM_BUILDYOUROWN_PRICE + EXTRA_FEE_PER_TOPPING * toppings.size();
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

    /**
     * Testing if a large buildyourown pizza with 2 toppings returns the correct price
     */
    @Test
    public void large_size_buildyourown_with_two_toppings_returns_correct_price() {
        // creating a buildyourown pizza with large size and 2 toppings
        ArrayList<Topping> toppings = new ArrayList<>(
                List.of(Topping.Cheddar, Topping.Onion)
        );
        BuildYourOwn customPizza = new BuildYourOwn(Size.LARGE, Crust.Pan, toppings);

        final double LARGE_BUILDYOUROWN_PRICE = 12.99;
        final double EXTRA_FEE_PER_TOPPING = 1.59;

        double expected_price = LARGE_BUILDYOUROWN_PRICE + EXTRA_FEE_PER_TOPPING * toppings.size();
        double actual_price = customPizza.price();

        assertEquals(expected_price, actual_price);
    }

}