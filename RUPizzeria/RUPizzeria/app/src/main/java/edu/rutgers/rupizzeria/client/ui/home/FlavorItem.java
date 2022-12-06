package edu.rutgers.rupizzeria.client.ui.home;

import edu.rutgers.rupizzeria.main.core.types.Flavor;

public class FlavorItem {

    private int flavorImageResId;

    private Flavor flavor;
    private String flavorPrice;

    private String flavorDescription;

    public FlavorItem(int flavorImageResId, Flavor flavor, String flavorPrice, String flavorDescription) {
        this.flavor = flavor;

        this.flavorImageResId = flavorImageResId;
        this.flavorPrice = flavorPrice;
        this.flavorDescription = flavorDescription;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public int getFlavorImageResId() {
        return flavorImageResId;
    }

    public String getFlavorPrice() {
        return flavorPrice;
    }

    public String getFlavorDescription() {
        return flavorDescription;
    }
}
