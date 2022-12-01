package edu.rutgers.rupizzeria.ui.home;

public class FlavorItem {

    private int flavorImageResId;

    private String flavorName;
    private String flavorPrice;

    private String flavorDescription;

    public FlavorItem(int flavorImageResId, String flavorName, String flavorPrice, String flavorDescription) {
        this.flavorImageResId = flavorImageResId;
        this.flavorName = flavorName;
        this.flavorPrice = flavorPrice;
        this.flavorDescription = flavorDescription;
    }

    public int getFlavorImageResId() {
        return flavorImageResId;
    }

    public String getFlavorName() {
        return flavorName;
    }

    public String getFlavorPrice() {
        return flavorPrice;
    }

    public String getFlavorDescription() {
        return flavorDescription;
    }
}
