package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Logger;
import edu.rutgers.rupizzeria.utils.Utils;

/**
 * ViewHolder for the items recyclerview in the cart view
 * which stores the list of pizzas in the cart
 * subclass of the GenericViewHolder
 * @author Michael Liu, Genfu Liu
 */
public class CartViewHolder extends GenericViewHolder<Pizza> {

    /**
     * ImageView for displaying the current item's image
     */
    private ImageView cartItemImage;

    /**
     * TextView for displaying the item name
     */
    private TextView cartItemNameText;

    /**
     * TextView for displaying the price of the item
     */
    private TextView cartItemPriceText;

    /**
     * TextView for displaying the type of crust/description of the item
     */
    private TextView cartItemDescriptionText;

    /**
     * ImageButton for removing the item from the cart.
     */
    private ImageButton cartItemDelete;

    /**
     * Parent of the entire item viewholder
     */
    private ConstraintLayout parent;

    /**
     * Constructor to find all the corresponding view elements and initializing the fields
     * @param itemView Current list item's view to pass into the parent's constructor
     */
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        cartItemImage = itemView.findViewById(R.id.cart_item_image);
        cartItemNameText = itemView.findViewById(R.id.cart_item_name);
        cartItemPriceText = itemView.findViewById(R.id.cart_item_price);
        cartItemDescriptionText = itemView.findViewById(R.id.cart_item_description);
        cartItemDelete = itemView.findViewById(R.id.cart_item_delete_button);

        parent = itemView.findViewById(R.id.cart_item_parent);
    }

    /**
     * Overridden onBind method to initialize all the view elements
     * when they're created with their data
     * @param cartItem
     */
    @Override
    public void onBind(Pizza cartItem) {
        cartItemImage.setImageResource(Utils.getPizzaImage(cartItem.getStyle(), cartItem.getFlavor()));

        cartItemNameText.setText(cartItem.toString());
        cartItemPriceText.setText(Utils.formatCurrency(cartItem.price()));
        cartItemDescriptionText.setText("");

        cartItemDelete.setOnClickListener(v -> onDeleteCartItem(cartItem));
        parent.setOnClickListener(v -> onClickCartItem(cartItem));
    }

    /**
     * This method will be called when the user clicks on this item
     * Unimplemented for now
     * @param cartItem The current item that this holder is storing
     */
    public void onClickCartItem(Pizza cartItem) {
        // not implemented
    }

    /**
     * This will be called when the user clicks on the delete item button
     * @param cartItem The current item that this holder is storing
     */
    public void onDeleteCartItem(Pizza cartItem) {
        DialogInterface.OnClickListener onConfirmRemoveFromCart = (dialog, which) -> {
            StoreManager.getInstance().removeFromCart(cartItem);

            Toast.makeText(currentContext, "Item successfully removed!", Toast.LENGTH_SHORT).show();
        };

        DialogInterface.OnClickListener onCancelRemoveFromCart = (dialog, which) -> dialog.cancel();

        Logger.logAlertConfirmation(currentContext, "Remove from Cart", "Are you sure you want to remove this item from your cart?", onConfirmRemoveFromCart, onCancelRemoveFromCart);
    }
}
