package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Utils;

public class StoreViewHolder extends GenericViewHolder<Pizza> {

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
    public void onBind(Pizza cartItem) {
        storeItemImage.setImageResource(Utils.getPizzaImage(cartItem.getStyle(), cartItem.getFlavor()));

        storeItemNameText.setText(cartItem.toString());
        storeItemPriceText.setText(Utils.formatCurrency(cartItem.price()));
        storeItemDescriptionText.setText("A cartItem");

        parent.setOnClickListener(v -> onClickCartItem(cartItem));
    }

    public void onClickCartItem(Pizza cartItem) {
        Toast.makeText(currentContext, cartItem.toString(), Toast.LENGTH_SHORT).show();
    }
}
