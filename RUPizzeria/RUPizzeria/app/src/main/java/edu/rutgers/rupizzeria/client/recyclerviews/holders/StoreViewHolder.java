package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    private TextView storeItemCrustText;

    private ListView storeItemToppingsList;

    private ConstraintLayout parent;

    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);

        storeItemImage = itemView.findViewById(R.id.store_item_image);
        storeItemNameText = itemView.findViewById(R.id.store_item_name);
        storeItemPriceText = itemView.findViewById(R.id.store_item_price);
        storeItemCrustText = itemView.findViewById(R.id.store_item_crust);
        storeItemToppingsList = itemView.findViewById(R.id.store_item_toppings);

        parent = itemView.findViewById(R.id.store_item_parent);
    }

    @Override
    public void onBind(Pizza cartItem) {
        storeItemImage.setImageResource(Utils.getPizzaImage(cartItem.getStyle(), cartItem.getFlavor()));

        storeItemNameText.setText(cartItem.toString());
        storeItemPriceText.setText(Utils.formatCurrency(cartItem.price()));
        storeItemCrustText.setText(cartItem.getCrust() + " Crust");

        parent.setOnClickListener(v -> onClickCartItem(cartItem));
    }

    public void onClickCartItem(Pizza cartItem) {

    }
}
