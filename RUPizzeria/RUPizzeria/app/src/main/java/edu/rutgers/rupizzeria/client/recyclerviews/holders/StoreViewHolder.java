package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Utils;

/**
 * ViewHolder for the store recyclerview which
 * lists all the items in the currently selected order
 * @author Michael Liu, Genfu Liu
 */
public class StoreViewHolder extends GenericViewHolder<Pizza> {

    /**
     * ImageView to display the image of the current store item
     */
    private ImageView storeItemImage;

    /**
     * TextView to display the name of the store item
     */
    private TextView storeItemNameText;

    /**
     * TextView to display the price of the store item
     */
    private TextView storeItemPriceText;

    /**
     * TextView to display the chosen crust for the store item
     */
    private TextView storeItemCrustText;

    /**
     * Parent of the entire viewholder
     */
    private ConstraintLayout parent;

    /**
     * Constructor to find all the corresponding view elements and initializing the fields
     * @param itemView Current list item's view to pass into the parent's constructor
     */
    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);

        storeItemImage = itemView.findViewById(R.id.store_item_image);
        storeItemNameText = itemView.findViewById(R.id.store_item_name);
        storeItemPriceText = itemView.findViewById(R.id.store_item_price);
        storeItemCrustText = itemView.findViewById(R.id.store_item_crust);

        parent = itemView.findViewById(R.id.store_item_parent);
    }

    /**
     * Overridden onBind method to initialize all the view elements
     * when they're created with their data
     * @param cartItem the cart item that is binded to this viewholder
     */
    @Override
    public void onBind(Pizza cartItem) {
        storeItemImage.setImageResource(Utils.getPizzaImage(cartItem.getStyle(), cartItem.getFlavor()));

        storeItemNameText.setText(cartItem.toString());
        storeItemPriceText.setText(Utils.formatCurrency(cartItem.price()));
        storeItemCrustText.setText(cartItem.getCrust() + " Crust");

        parent.setOnClickListener(v -> onClickCartItem(cartItem));
    }

    /**
     * This is called whenever the user clicks on the current cart item view
     * (unimplemented for now)
     * @param cartItem the cart item the user clicks
     */
    public void onClickCartItem(Pizza cartItem) {
        // not implemented
    }
}
