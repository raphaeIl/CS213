package edu.rutgers.rupizzeria.utils;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Style;

/**
 * Utilities class for helper methods
 * @author Michael Liu, Genfu Liu
 */
public class Utils {

    /**
     * Capitalizes a word
     * @param str the word to be capitalized
     * @return the capitalized word
     */
    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() +
                str.substring(1).toLowerCase();
    }

    /**
     * Formats a currency number to 2 decimal places
     * @param currency the currency to be formatted
     * @return the formatted currency
     */
    public static String formatCurrency(double currency) {
        return String.format("$%.2f", currency);
    }

    /**
     * Gets the pizza image resource id from the pizza's style and flavor
     * @param pizzaStyle the style of the pizza
     * @param pizzaFlavor the flavor of the pizza
     * @return the image resource according to the style and flavor, -1 if not found?
     */
    public static int getPizzaImage(Style pizzaStyle, Flavor pizzaFlavor) {
        if (pizzaStyle == Style.Chicago) {
            switch (pizzaFlavor) {
                case BBQ_Chicken:
                    return R.drawable.chicago_bbq_chicken;
                case Deluxe:
                    return R.drawable.chicago_deluxe;
                case Meatzza:
                    return R.drawable.chicago_meatzza;
                case Build_Your_Own:
                    return R.drawable.build_your_own;
            }
        } else if (pizzaStyle == Style.NY) {
            switch (pizzaFlavor) {
                case BBQ_Chicken:
                    return R.drawable.ny_bbq_chicken;
                case Deluxe:
                    return R.drawable.ny_deluxe;
                case Meatzza:
                    return R.drawable.ny_meatzza;
                case Build_Your_Own:
                    return R.drawable.build_your_own;
            }
        }

        return -1;
    }
}
