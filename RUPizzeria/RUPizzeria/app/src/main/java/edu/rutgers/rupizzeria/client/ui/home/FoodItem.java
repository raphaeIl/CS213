package edu.rutgers.rupizzeria.client.ui.home;

public class FoodItem {

    private int foodImageResId;

    private String foodName;
    private String foodInfo;

    public FoodItem(int foodImageResId, String foodName, String foodInfo) {
        this.foodImageResId = foodImageResId;
        this.foodName = foodName;
        this.foodInfo = foodInfo;
    }

    public int getFoodImageResId() {
        return foodImageResId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

}
