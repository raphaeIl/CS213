package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import edu.rutgers.rupizzeria.SelectFlavorActivity;
import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.client.ui.home.FoodItem;

public class FoodsViewHolder extends GenericViewHolder<FoodItem> {

    private ImageView foodImage;
    private TextView foodNameText;
    private TextView foodInfoText;

    private CardView parent;

    public FoodsViewHolder(@NonNull View itemView) {
        super(itemView);

        foodImage = itemView.findViewById(R.id.recycler_item_food_image);
        foodInfoText = itemView.findViewById(R.id.recycler_item_food_info_text);
        foodNameText = itemView.findViewById(R.id.recycler_item_food_name_text);
        parent = itemView.findViewById(R.id.foods_recycler_view_item_parent);
    }

    @Override
    public void onBind(FoodItem foodItem) {
        foodImage.setImageResource(foodItem.getFoodImageResId());
        foodInfoText.setText(foodItem.getFoodInfo());
        foodNameText.setText(foodItem.getFoodName());

        parent.setOnClickListener(v -> onSelectFood(foodItem));
    }

    public void onSelectFood(FoodItem foodItem) {
        Toast.makeText(this.currentContext, foodItem.getFoodInfo(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this.currentContext, SelectFlavorActivity.class);

        intent.putExtra("foodName", foodItem.getFoodName());
        this.currentContext.startActivity(intent);
//        ((Activity)currentContext).overridePendingTransition(); // custom activity transition animatino
    }
}
