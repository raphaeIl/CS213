package edu.rutgers.rupizzeria.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.databinding.FragmentStoreBinding;
import edu.rutgers.rupizzeria.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.recyclerviews.holders.CartViewHolder;
import edu.rutgers.rupizzeria.recyclerviews.holders.StoreViewHolder;
import edu.rutgers.rupizzeria.ui.cart.CartItem;

public class StoreFragment extends Fragment {

    private FragmentStoreBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StoreViewModel notificationsViewModel =
                new ViewModelProvider(this).get(StoreViewModel.class);

        binding = FragmentStoreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Spinner ordersSpinner = root.findViewById(R.id.store_order_number_spinner);

        List<Integer> orderNumbers = Arrays.asList(-47890543, 324980, -438443, 1);
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, orderNumbers);

        ordersSpinner.setAdapter(spinnerAdapter);

        // Items
        RecyclerView storeRecyclerView = root.findViewById(R.id.store_recycler_view);
        List<CartItem> store = new ArrayList<>();

        store.add(new CartItem(R.drawable.chicago_deluxe, "Large Deluxe Pizza • Chicago Style", "Stuffed Crust", "$14.99"));
        store.add(new CartItem(R.drawable.chicago_bbq_chicken, "Medium BBQ Chicken Pizza • Chicago Style", "Pan Crust", "$13.99"));

        store.add(new CartItem(R.drawable.chicago_bbq_chicken, "Medium BBQ Chicken Pizza • Chicago Style", "Pan Crust", "$13.99"));
        store.add(new CartItem(R.drawable.chicago_bbq_chicken, "Medium BBQ Chicken Pizza • Chicago Style", "Pan Crust", "$13.99"));

        GenericRecyclerViewAdapter<CartItem, StoreViewHolder> storeRecyclerViewAdapter =
                new GenericRecyclerViewAdapter<>(store, root.getContext(), StoreViewHolder.class, R.layout.store_recycler_view_item);

        storeRecyclerView.setAdapter(storeRecyclerViewAdapter);
        storeRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}