package edu.rutgers.rupizzeria.main.core.types;

import androidx.annotation.NonNull;

/**
 * enum representing different core.types of pizza flavors
 * @author Michael Liu, Genfu Liu
 */
public enum Flavor {
    BBQ_Chicken,
    Deluxe,
    Meatzza,
    Build_Your_Own;

    /**
     * Overridden toString method to convert the enum constant
     * to a nice looking display string
     * @return the formatted display string
     */
    @NonNull
    @Override
    public String toString() {
        return super.toString().replace('_', ' ');
    }
}
