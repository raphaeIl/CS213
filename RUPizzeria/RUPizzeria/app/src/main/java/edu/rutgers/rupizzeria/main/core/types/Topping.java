package edu.rutgers.rupizzeria.main.core.types;

import androidx.annotation.NonNull;

/**
 * enum representing different core.types of pizza toppings
 * @author Michael Liu, Genfu Liu
 */
public enum Topping {
    Sausage,
    Pepperoni,
    Green_Pepper,
    Onion,
    Mushroom,
    BBQ_Chicken,
    Provolone,
    Cheddar,
    Beef,
    Ham,
    Bacon,
    Black_Olives,
    Pineapple;

    @NonNull
    @Override
    public String toString() {
        return super.toString().replace('_', ' ');
    }
}
