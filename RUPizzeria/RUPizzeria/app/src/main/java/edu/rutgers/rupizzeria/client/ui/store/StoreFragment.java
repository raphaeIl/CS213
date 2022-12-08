package edu.rutgers.rupizzeria.client.ui.store;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.StoreViewHolder;
import edu.rutgers.rupizzeria.databinding.FragmentStoreBinding;
import edu.rutgers.rupizzeria.main.core.customizable.Order;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Logger;
import edu.rutgers.rupizzeria.utils.Utils;

public class StoreFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentStoreBinding binding;

    private Spinner ordersSpinner;
    private ArrayAdapter<Integer> ordersSpinnerAdapter;
    private List<Integer> orderIds;

    private RecyclerView storeRecyclerView;
    private GenericRecyclerViewAdapter<Pizza, StoreViewHolder> storeRecyclerViewAdapter;
    private List<Pizza> orderItems;

    private StoreManager storeManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStoreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        storeManager = StoreManager.getInstance();

        orderIds = new ArrayList<>(storeManager.getOrderHistory().getAllOrders().keySet());
        ordersSpinner = root.findViewById(R.id.store_order_number_spinner);
        ordersSpinnerAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, orderIds);

        ordersSpinner.setAdapter(ordersSpinnerAdapter);

        // Items
        storeRecyclerView = root.findViewById(R.id.store_recycler_view);
        orderItems = new ArrayList<>();
        storeRecyclerViewAdapter = new GenericRecyclerViewAdapter<>(orderItems, root.getContext(), StoreViewHolder.class, R.layout.store_recycler_view_item);

        storeRecyclerView.setAdapter(storeRecyclerViewAdapter);
        storeRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        root.findViewById(R.id.store_cancel_order_button).setOnClickListener(v -> onCancelCurrentOrder());
        ordersSpinner.setOnItemSelectedListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        orderItems.clear();

        Order order = storeManager.getOrderHistory().getAllOrders().get(orderIds.get(position));
        orderItems.addAll(order.getAllItems());
        storeRecyclerViewAdapter.notifyDataSetChanged();

        ((TextView)binding.getRoot().findViewById(R.id.store_order_info)).setText(
                String.format("%s items • %s total", orderItems.size(), Utils.formatCurrency(order.getTotalPrice()))
        );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        ((TextView)binding.getRoot().findViewById(R.id.store_order_info)).setText("");
    }

    public void onCancelCurrentOrder() {
        if (ordersSpinner.getSelectedItem() == null) {
            Toast.makeText(getContext(), "Please select a order", Toast.LENGTH_SHORT).show();
            return;
        }

        DialogInterface.OnClickListener onConfirmCancelOrder = (dialog, which) -> {
            storeManager.cancelOrder((int)ordersSpinner.getSelectedItem());
            orderIds.remove((Integer)ordersSpinner.getSelectedItem());
            orderItems.clear();


            ordersSpinnerAdapter.notifyDataSetChanged();
            storeRecyclerViewAdapter.notifyDataSetChanged();

            Toast.makeText(getContext(), "The order has been cancelled!", Toast.LENGTH_SHORT).show();
        };

        DialogInterface.OnClickListener onCancelCancelOrder = (dialog, which) -> dialog.cancel();

        Logger.logAlertConfirmation(getContext(), "Cancel order", "Are you sure you want to cancel the current order?", onConfirmCancelOrder, onCancelCancelOrder);
    }

}