package edu.rutgers.rupizzeria.client.ui.cart;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.CartViewHolder;
import edu.rutgers.rupizzeria.client.ui.home.KeyValueItem;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.KeyValueViewHolder;
import edu.rutgers.rupizzeria.databinding.FragmentCartBinding;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Logger;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    private TextView orderNumberText;

    private GenericRecyclerViewAdapter<Pizza, CartViewHolder> cartRecyclerViewAdapter;
    private List<Pizza> cartItems;

    private GenericRecyclerViewAdapter<KeyValueItem<String, Double>, KeyValueViewHolder> summaryRecyclerViewAdapter;
    private List<KeyValueItem<String, Double>> summary;

    private StoreManager storeManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = root.getContext();
        storeManager = StoreManager.getInstance();

        orderNumberText = root.findViewById(R.id.cart_order_number);
        orderNumberText.setText("Order #: " + storeManager.getCurrentCart().getOrderId());

        // Items
        RecyclerView cartRecyclerView = root.findViewById(R.id.cart_items_recycler_view);

        cartItems = new ArrayList<>(StoreManager.getInstance().getCurrentCart().getAllItems());
        cartRecyclerViewAdapter = new GenericRecyclerViewAdapter<>(cartItems, context, CartViewHolder.class, R.layout.cart_recycler_view_item);

        cartRecyclerView.setAdapter(cartRecyclerViewAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        // Summary
        RecyclerView summaryRecyclerView = root.findViewById(R.id.cart_summary_recycler_view);
        summary = new ArrayList<>();

        summary.add(new KeyValueItem<>("Subtotal",  storeManager.getSubtotal(), Size.SMALL));
        summary.add(new KeyValueItem<>("Delivery Fee", 0.00, Size.SMALL));
        summary.add(new KeyValueItem<>("Sales Tax", storeManager.getSalesTax(), Size.SMALL));
        summary.add(new KeyValueItem<>("Total", storeManager.getTotal(), Size.LARGE));

        summaryRecyclerViewAdapter = new GenericRecyclerViewAdapter<>(summary, context, KeyValueViewHolder.class, R.layout.recycler_view_key_value_item);

        summaryRecyclerView.setAdapter(summaryRecyclerViewAdapter);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        storeManager.addCartItemChangedListener((actionChanged, isRemoved) -> onCartItemChanged(actionChanged, isRemoved));

        root.findViewById(R.id.cart_clear_cart_button).setOnClickListener(v -> onClearCart());
        root.findViewById(R.id.cart_place_order_button).setOnClickListener(v -> onPlaceOrder(cartItems, cartRecyclerViewAdapter, summary, summaryRecyclerViewAdapter));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Used for placing an order,
     * called when the place order button is clicked
     */
    public void onPlaceOrder(List<Pizza> cartItems, GenericRecyclerViewAdapter cartAdapter, List<KeyValueItem<String, Double>> summary, GenericRecyclerViewAdapter summaryAdapter) {
        if (storeManager.getCurrentCart().isEmpty()) {
            Toast.makeText(getContext(), "Cart is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        DialogInterface.OnClickListener onConfirmPlaceOrder = (dialog, which) -> {
            storeManager.placeOrder();
            Toast.makeText(getContext(), "Your order has been placed!", Toast.LENGTH_SHORT).show();

            cartItems.clear();
            cartAdapter.notifyDataSetChanged();

            summary.get(0).setValue(storeManager.getSubtotal());
            summary.get(2).setValue(storeManager.getSalesTax());
            summary.get(3).setValue(storeManager.getTotal());

            summaryAdapter.notifyDataSetChanged();

            orderNumberText.setText("Order #: " + storeManager.getCurrentCart().getOrderId());
        };

        DialogInterface.OnClickListener onCancelPlaceOrder = (dialog, which) -> dialog.cancel();

        Logger.logAlertConfirmation(getContext(), "Place order", "Place Order?", onConfirmPlaceOrder, onCancelPlaceOrder);
    }

    public void onClearCart() {
        DialogInterface.OnClickListener onConfirmClearCart = (dialog, which) -> {
            StoreManager.getInstance().clearCart();

            Toast.makeText(getContext(), "Your cart has been cleared!", Toast.LENGTH_SHORT).show();
        };

        DialogInterface.OnClickListener onCancelClearCart = (dialog, which) -> dialog.cancel();

        Logger.logAlertConfirmation(getContext(), "Clear your cart", "Are you sure you want to clear all items from your cart?", onConfirmClearCart, onCancelClearCart);
    }

    public void onCartItemChanged(Pizza itemChanged, boolean isRemoved) {
        if (!isRemoved)
            cartItems.add(itemChanged);
        else
            cartItems.remove(itemChanged);

        cartRecyclerViewAdapter.notifyDataSetChanged();

        summary.get(0).setValue(storeManager.getSubtotal());
        summary.get(2).setValue(storeManager.getSalesTax());
        summary.get(3).setValue(storeManager.getTotal());

        summaryRecyclerViewAdapter.notifyDataSetChanged();
    }
}