package edu.rutgers.rupizzeria.utils;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Style;

public class Utils {

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() +
                str.substring(1).toLowerCase();
    }

    public static String formatCurrency(double currency) {
        return String.format("$%.2f", currency);
    }

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
