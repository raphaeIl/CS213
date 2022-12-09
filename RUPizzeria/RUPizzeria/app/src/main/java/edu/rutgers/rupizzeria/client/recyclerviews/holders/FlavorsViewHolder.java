package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.rutgers.rupizzeria.OrderFoodActivity;
import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.SelectFlavorActivity;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Style;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.utils.Utils;

/**
 * ViewHolder for the flavors recyclerview which
 * lists the available flavors for the user to choose
 * @author Michael Liu, Genfu Liu
 */
public class FlavorsViewHolder extends GenericViewHolder<Flavor> {

    /**
     * ImageView for displaying the current flavor's image
     */
    private ImageView flavorImage;

    /**
     * TextView for displaying the flavor name
     */
    private TextView flavorNameText;

    /**
     * TextView for displaying the flavor price
     */
    private TextView flavorPriceText;

    /**
     * TextView for displaying the description for the flavor
     */
    private TextView flavorDescriptionText;

    /**
     * Parent of the entire viewholder
     */
    private ConstraintLayout parent;

    /**
     * Constructor to find all the corresponding view elements and initializing the fields
     * @param itemView Current list item's view to pass into the parent's constructor
     */
    public FlavorsViewHolder(@NonNull View itemView) {
        super(itemView);

        flavorImage = itemView.findViewById(R.id.select_flavor_list_image);
        flavorNameText = itemView.findViewById(R.id.cart_item_name);
        flavorPriceText = itemView.findViewById(R.id.cart_item_price);
        flavorDescriptionText = itemView.findViewById(R.id.cart_item_description);

        parent = itemView.findViewById(R.id.select_flavor_item_parent);
    }

    /**
     * Overridden onBind method to initialize all the view elements
     * when they're created with their data
     * @param flavor The current flavor
     */
    @Override
    public void onBind(Flavor flavor) {
        Style currentStyle = SelectFlavorActivity.currentStyle;

        flavorImage.setImageResource(Utils.getPizzaImage(currentStyle, flavor));
        flavorNameText.setText(flavor + " Pizza");
        flavorPriceText.setText(Utils.formatCurrency(0.00));
        flavorDescriptionText.setText(String.format("A %s Style %s Pizza", currentStyle, flavor)); // string with formatting

        parent.setOnClickListener(v -> onSelectFlavor(currentStyle, flavor));
    }

    /**
     * This is called when user clicks this item and selects this flavor
     * @param style The style of the pizza that was chosen on the previous window
     * @param flavor The flavor that the user chooses
     */
    public void onSelectFlavor(Style style, Flavor flavor) {
        StoreManager.getInstance().selectPizza(style, flavor, Size.SMALL);

        // next activity
        Intent intent = new Intent(this.currentContext, OrderFoodActivity.class);
        this.currentContext.startActivity(intent);
    }
}
