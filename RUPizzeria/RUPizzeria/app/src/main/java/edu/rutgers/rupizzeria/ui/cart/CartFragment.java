package edu.rutgers.rupizzeria.ui.cart;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.databinding.FragmentCartBinding;
import edu.rutgers.rupizzeria.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.recyclerviews.holders.CartViewHolder;
import edu.rutgers.rupizzeria.recyclerviews.holders.KeyValueViewHolder;
import edu.rutgers.rupizzeria.types.Size;
import edu.rutgers.rupizzeria.ui.home.KeyValueItem;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CartViewModel dashboardViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = root.getContext();

        // Items
        RecyclerView cartRecyclerView = root.findViewById(R.id.cart_items_recycler_view);
        List<CartItem> cart = new ArrayList<>();

        cart.add(new CartItem(R.drawable.chicago_deluxe, "Large Deluxe Pizza • Chicago Style", "Stuffed Crust", "$14.99"));
        cart.add(new CartItem(R.drawable.chicago_bbq_chicken, "Medium BBQ Chicken Pizza • Chicago Style", "Pan Crust", "$13.99"));

        cart.add(new CartItem(R.drawable.chicago_bbq_chicken, "Medium BBQ Chicken Pizza • Chicago Style", "Pan Crust", "$13.99"));
        cart.add(new CartItem(R.drawable.chicago_bbq_chicken, "Medium BBQ Chicken Pizza • Chicago Style", "Pan Crust", "$13.99"));


        GenericRecyclerViewAdapter<CartItem, CartViewHolder> cartRecyclerViewAdapter =
                new GenericRecyclerViewAdapter<>(cart, context, CartViewHolder.class, R.layout.cart_recycler_view_item);

//        cartRecyclerViewAdapter.notifyDataSetChanged();

        cartRecyclerView.setAdapter(cartRecyclerViewAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        // Summary
        RecyclerView summaryRecyclerView = root.findViewById(R.id.cart_summary_recycler_view);
        List<KeyValueItem<String, String>> summary = new ArrayList<>();

        summary.add(new KeyValueItem<>("Subtotal", "$38.98", Size.SMALL));
        summary.add(new KeyValueItem<>("Delivery Fee", "$0.00", Size.SMALL));
        summary.add(new KeyValueItem<>("Sales Tax", "$2.58", Size.SMALL));
        summary.add(new KeyValueItem<>("Total", "$41.56", Size.LARGE));

        GenericRecyclerViewAdapter<KeyValueItem<String, String>, KeyValueViewHolder> summaryRecyclerViewAdapter =
                new GenericRecyclerViewAdapter<>(summary, context, KeyValueViewHolder.class, R.layout.recycler_view_key_value_item);

        summaryRecyclerView.setAdapter(summaryRecyclerViewAdapter);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        root.findViewById(R.id.cart_clear_cart_button).setOnClickListener(v -> {
            cart.clear();
            cartRecyclerViewAdapter.notifyDataSetChanged();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}