package edu.rutgers.rupizzeria.client.ui.cart;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        List<Pizza> cartItems = new ArrayList<>(StoreManager.getInstance().getCurrentCart().getAllItems());
        GenericRecyclerViewAdapter<Pizza, CartViewHolder> cartRecyclerViewAdapter = new GenericRecyclerViewAdapter<>(cartItems, context, CartViewHolder.class, R.layout.cart_recycler_view_item);

        cartRecyclerView.setAdapter(cartRecyclerViewAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        storeManager.addCartItemChangedListener((itemChanged, isRemoved) -> {
            if (!isRemoved)
                cartItems.add(itemChanged);
            else
                cartItems.remove(itemChanged);

            cartRecyclerViewAdapter.notifyDataSetChanged();
        });

        // Summary
        RecyclerView summaryRecyclerView = root.findViewById(R.id.cart_summary_recycler_view);
        List<KeyValueItem<String, Double>> summary = new ArrayList<>();

        summary.add(new KeyValueItem<>("Subtotal",  storeManager.getSubtotal(), Size.SMALL));
        summary.add(new KeyValueItem<>("Delivery Fee", 0.00, Size.SMALL));
        summary.add(new KeyValueItem<>("Sales Tax", storeManager.getSalesTax(), Size.SMALL));
        summary.add(new KeyValueItem<>("Total", storeManager.getTotal(), Size.LARGE));

        GenericRecyclerViewAdapter<KeyValueItem<String, Double>, KeyValueViewHolder> summaryRecyclerViewAdapter = new GenericRecyclerViewAdapter<>(summary, context, KeyValueViewHolder.class, R.layout.recycler_view_key_value_item);

        summaryRecyclerView.setAdapter(summaryRecyclerViewAdapter);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        storeManager.addCartItemChangedListener((actionChanged, isRemoved) -> {
            summary.get(0).setValue(storeManager.getSubtotal());
            summary.get(2).setValue(storeManager.getSalesTax());
            summary.get(3).setValue(storeManager.getTotal());

            summaryRecyclerViewAdapter.notifyDataSetChanged();
        });

        root.findViewById(R.id.cart_clear_cart_button).setOnClickListener(v -> StoreManager.getInstance().clearCart());
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
            Logger.log("Cart is empty!");
            return;
        }



        storeManager.placeOrder();
        Logger.log("Order has been successfully placed!");

        cartItems.clear();
        cartAdapter.notifyDataSetChanged();

        summary.get(0).setValue(storeManager.getSubtotal());
        summary.get(2).setValue(storeManager.getSalesTax());
        summary.get(3).setValue(storeManager.getTotal());

        summaryAdapter.notifyDataSetChanged();

        orderNumberText.setText("Order #: " + storeManager.getCurrentCart().getOrderId());
    }
}