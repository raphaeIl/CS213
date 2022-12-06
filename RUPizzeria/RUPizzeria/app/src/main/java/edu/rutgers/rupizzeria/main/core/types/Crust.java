package edu.rutgers.rupizzeria.main.core.types;

import androidx.annotation.NonNull;

/**
 * enum representing different core.types of pizza Crusts
 * @author Michael Liu, Genfu Liu
 */
public enum Crust {
    Deep_Dish,
    Pan,
    Stuffed,
    Brooklyn,
    Thin,
    Hand_tossed;

    @NonNull
    @Override
    public String toString() {
        return super.toString().replace('_', ' ');
    }
}
