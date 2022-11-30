package edu.rutgers.rupizzeria.ui.home;

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

    public void setFoodImageResId(int foodImageResId) {
        this.foodImageResId = foodImageResId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }
}
