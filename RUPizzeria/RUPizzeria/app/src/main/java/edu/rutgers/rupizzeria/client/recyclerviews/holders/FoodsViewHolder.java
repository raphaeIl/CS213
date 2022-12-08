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
import edu.rutgers.rupizzeria.main.core.types.Style;

public class FoodsViewHolder extends GenericViewHolder<Style> {

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
    public void onBind(Style foodStyle) {
        foodImage.setImageResource(foodStyle == Style.Chicago ? R.drawable.main_menu_chicago_style : R.drawable.main_menu_ny_style);
        foodInfoText.setText("0 min • $0 Delivery fee");
        foodNameText.setText(String.format("\uD83C\uDF55 %s Style Pizza", foodStyle));

        parent.setOnClickListener(v -> onSelectFood(foodStyle));
    }

    public void onSelectFood(Style foodStyle) {
        Intent intent = new Intent(this.currentContext, SelectFlavorActivity.class);

        intent.putExtra("food_style", foodStyle);
        this.currentContext.startActivity(intent);
    }
}
