package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import edu.rutgers.rupizzeria.SelectFlavorActivity;
import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Style;

/**
 * ViewHolder for the foods recyclerview which
 * lists the available foods for the user to choose
 * @author Michael Liu, Genfu Liu
 */
public class FoodsViewHolder extends GenericViewHolder<Style> {

    /**
     * ImageView for displaying the food's image
     */
    private ImageView foodImage;

    /**
     * TextView for displaying the name of the food
     */
    private TextView foodNameText;

    /**
     * TextView for displaying the info for the food
     */
    private TextView foodInfoText;

    /**
     * Parent of this view item which is a CardView
     */
    private CardView parent;

    /**
     * Constructor to find all the corresponding view elements and initializing the fields
     * @param itemView Current list item's view to pass into the parent's constructor
     */
    public FoodsViewHolder(@NonNull View itemView) {
        super(itemView);

        foodImage = itemView.findViewById(R.id.recycler_item_food_image);
        foodInfoText = itemView.findViewById(R.id.recycler_item_food_info_text);
        foodNameText = itemView.findViewById(R.id.recycler_item_food_name_text);
        parent = itemView.findViewById(R.id.foods_recycler_view_item_parent);
    }

    /**
     * Overridden onBind method to initialize all the view elements
     * when they're created with their data
     * @param foodStyle The food style this item is holding
     */
    @Override
    public void onBind(Style foodStyle) {
        foodImage.setImageResource(foodStyle == Style.Chicago ? R.drawable.main_menu_chicago_style : R.drawable.main_menu_ny_style);
        foodInfoText.setText("0 min • $0 Delivery fee");
        foodNameText.setText(String.format("\uD83C\uDF55 %s Style Pizza", foodStyle));

        parent.setOnClickListener(v -> onSelectFood(foodStyle));
    }

    /**
     * This is called whenever the user clicks on this card and chooses this food style
     * @param foodStyle the food style the user selects
     */
    public void onSelectFood(Style foodStyle) {
        Intent intent = new Intent(this.currentContext, SelectFlavorActivity.class);

        intent.putExtra("food_style", foodStyle);
        this.currentContext.startActivity(intent);
    }
}
