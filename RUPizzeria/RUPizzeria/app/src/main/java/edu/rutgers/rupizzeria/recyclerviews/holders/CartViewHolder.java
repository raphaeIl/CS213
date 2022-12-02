package edu.rutgers.rupizzeria.recyclerviews.holders;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.rutgers.rupizzeria.OrderFoodActivity;
import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.ui.cart.CartItem;
import edu.rutgers.rupizzeria.ui.home.FlavorItem;
import edu.rutgers.rupizzeria.ui.home.FoodItem;

public class CartViewHolder extends GenericViewHolder<CartItem> {

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
    public void onBind(CartItem cartItem) {
        cartItemImage.setImageResource(cartItem.getCartItemImageResId());
        cartItemNameText.setText(cartItem.getCartItemName());
        cartItemPriceText.setText(cartItem.getCartItemPrice());
        cartItemDescriptionText.setText(cartItem.getCartItemDescription());

        cartItemDelete.setOnClickListener(v -> onDeleteCartItem(cartItem));
        parent.setOnClickListener(v -> onClickCartItem(cartItem));
    }

    public void onClickCartItem(CartItem cartItem) {
        Toast.makeText(currentContext, cartItem.getCartItemName(), Toast.LENGTH_SHORT).show();
    }

    public void onDeleteCartItem(CartItem cartItem) {
        GenericRecyclerViewAdapter<CartItem, CartViewHolder> cartRecyclerViewAdapter =
                (GenericRecyclerViewAdapter<CartItem, CartViewHolder>)
                        ((RecyclerView)currentItemView.getRootView().findViewById(R.id.cart_items_recycler_view)).getAdapter();

            cartRecyclerViewAdapter.getItemsList().remove(cartItem);
            cartRecyclerViewAdapter.notifyDataSetChanged();

//            cartRecyclerViewAdapter.notifyItemRemoved(getAdapterPosition());
//            cartRecyclerViewAdapter.notifyItemRangeChanged(getAdapterPosition(), cartRecyclerViewAdapter.getItemCount());
    }
}
