package edu.rutgers.rupizzeria.recyclerviews.holders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.ui.cart.CartItem;

public class StoreViewHolder extends GenericViewHolder<CartItem>  {

    private ImageView storeItemImage;
    private TextView storeItemNameText;
    private TextView storeItemPriceText;
    private TextView storeItemDescriptionText;

    private ConstraintLayout parent;

    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);

        storeItemImage = itemView.findViewById(R.id.store_item_image);
        storeItemNameText = itemView.findViewById(R.id.store_item_name);
        storeItemPriceText = itemView.findViewById(R.id.store_item_price);
        storeItemDescriptionText = itemView.findViewById(R.id.store_item_description);

        parent = itemView.findViewById(R.id.store_item_parent);
    }

    @Override
    public void onBind(CartItem cartItem) {
        storeItemImage.setImageResource(cartItem.getCartItemImageResId());
        storeItemNameText.setText(cartItem.getCartItemName());
        storeItemPriceText.setText(cartItem.getCartItemPrice());
        storeItemDescriptionText.setText(cartItem.getCartItemDescription());

        parent.setOnClickListener(v -> onClickCartItem(cartItem));
    }

    public void onClickCartItem(CartItem cartItem) {
        Toast.makeText(currentContext, cartItem.getCartItemName(), Toast.LENGTH_SHORT).show();
    }
}
