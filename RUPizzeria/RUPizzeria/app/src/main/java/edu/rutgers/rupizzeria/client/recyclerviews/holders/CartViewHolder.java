package edu.rutgers.rupizzeria.client.recyclerviews.holders;

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
import edu.rutgers.rupizzeria.utils.Utils;

public class CartViewHolder extends GenericViewHolder<Pizza> {

    private ImageView cartItemImage;
    private TextView cartItemNameText;
    private TextView cartItemPriceText;
    private TextView cartItemDescriptionText;

    private ImageButton cartItemDelete;

    private ConstraintLayout parent;

    private View currentItemView;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        cartItemImage = itemView.findViewById(R.id.cart_item_image);
        cartItemNameText = itemView.findViewById(R.id.cart_item_name);
        cartItemPriceText = itemView.findViewById(R.id.cart_item_price);
        cartItemDescriptionText = itemView.findViewById(R.id.cart_item_description);
        cartItemDelete = itemView.findViewById(R.id.cart_item_delete_button);

        parent = itemView.findViewById(R.id.cart_item_parent);
        currentItemView = itemView;
    }

    @Override
    public void onBind(Pizza cartItem) {
        cartItemImage.setImageResource(Utils.getPizzaImage(cartItem.getStyle(), cartItem.getFlavor()));

        cartItemNameText.setText(cartItem.toString());
        cartItemPriceText.setText(Utils.formatCurrency(cartItem.price()));
        cartItemDescriptionText.setText("A cartItem");

        cartItemDelete.setOnClickListener(v -> onDeleteCartItem(cartItem));
        parent.setOnClickListener(v -> onClickCartItem(cartItem));
    }

    public void onClickCartItem(Pizza cartItem) {
        Toast.makeText(currentContext, cartItem.toString(), Toast.LENGTH_SHORT).show();
    }

    public void onDeleteCartItem(Pizza cartItem) {
        StoreManager.getInstance().removeFromCart(cartItem);
    }
}
