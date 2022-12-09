package edu.rutgers.rupizzeria.client.ui.cart;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.CartViewHolder;
import edu.rutgers.rupizzeria.databinding.FragmentCartBinding;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Logger;

/**
 * This Fragment is part of the Main Activity
 * that is responsible for displaying all the available foods
 * as well as allowing the user to order food
 * @author Michael Liu, Genfu Liu
 */
public class CartFragment extends Fragment {

    /**
     * Auto generated binding object
     */
    private FragmentCartBinding binding;

    /**
     * TextView to display the current order number
     */
    private TextView orderNumberText;

    /**
     * Adapter for the Cart RecyclerView
     * declared here to be accessible throughout the whole class
     */
    private GenericRecyclerViewAdapter<Pizza, CartViewHolder> cartRecyclerViewAdapter;

    /**
     * The list of cart items that the cart recyclerview is displaying
     */
    private List<Pizza> cartItems;

    /**
     * Adapter for the summary ListView
     */
    private SummaryListAdapter summaryListViewAdapter;

    /**
     * List of KeyValue pairs that the summary recyclerView is displaying
     */
    private KeyValueItem<String, Double>[] summaryList;

    /**
     * StoreManager declared here for convince
     */
    private StoreManager storeManager;

    /**
     * Overridden method that is called when this fragment is created
     * Initializes all the textviews as well as the recyclerviews
     * @param inflater Inherited param
     * @param container Inherited param
     * @param savedInstanceState Inherited param
     * @return The root of this fragment
     */
    @Override
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
        ListView summaryListView = root.findViewById(R.id.cart_summary_list_view);

        summaryList = new KeyValueItem[4];

        summaryList[0] = new KeyValueItem<>("Subtotal",  storeManager.getSubtotal(), Size.SMALL);
        summaryList[1] = new KeyValueItem<>("Delivery Fee", 0.00, Size.SMALL);
        summaryList[2] = new KeyValueItem<>("Sales Tax", storeManager.getSalesTax(), Size.SMALL);
        summaryList[3] = new KeyValueItem<>("Total", storeManager.getTotal(), Size.LARGE);

        summaryListViewAdapter = new SummaryListAdapter(getContext(), R.id.cart_summary_list_view, summaryList);

        summaryListView.setAdapter(summaryListViewAdapter);

        storeManager.addCartChangedListener((actionChanged, isRemoved) -> onCartItemChanged(actionChanged, isRemoved));

        root.findViewById(R.id.cart_clear_cart_button).setOnClickListener(v -> onClearCart());
        root.findViewById(R.id.cart_place_order_button).setOnClickListener(v -> onPlaceOrder());

        return root;
    }

    /**
     * Overridden method that will be called when the view is destroyed
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Called when the user places the current oder
     * places the order and clears the cart to be ready for the next order
     */
    public void onPlaceOrder() {
        if (storeManager.getCurrentCart().isEmpty()) {
            Toast.makeText(getContext(), "Cart is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        DialogInterface.OnClickListener onConfirmPlaceOrder = (dialog, which) -> {
            storeManager.placeOrder();
            Toast.makeText(getContext(), "Your order has been placed!", Toast.LENGTH_SHORT).show();

            cartItems.clear();
            cartRecyclerViewAdapter.notifyDataSetChanged();

            updateSummaryDisplay();
            orderNumberText.setText("Order #: " + storeManager.getCurrentCart().getOrderId());
        };

        DialogInterface.OnClickListener onCancelPlaceOrder = (dialog, which) -> dialog.cancel();

        Logger.logAlertConfirmation(getContext(), "Place order", "Place Order?", onConfirmPlaceOrder, onCancelPlaceOrder);
    }

    /**
     * This is called when the user clears the cart
     */
    public void onClearCart() {
        DialogInterface.OnClickListener onConfirmClearCart = (dialog, which) -> {
            StoreManager.getInstance().clearCart();

            Toast.makeText(getContext(), "Your cart has been cleared!", Toast.LENGTH_SHORT).show();
        };

        DialogInterface.OnClickListener onCancelClearCart = (dialog, which) -> dialog.cancel();

        Logger.logAlertConfirmation(getContext(), "Clear your cart", "Are you sure you want to clear all items from your cart?", onConfirmClearCart, onCancelClearCart);
    }

    /**
     * This is the subscriber method that subscribes to the CartChangedListener in StoreManager
     * @param itemChanged The pizza item that was added/removed from the cart
     * @param isRemoved whether the item was removed or added
     */
    public void onCartItemChanged(Pizza itemChanged, boolean isRemoved) {
        if (!isRemoved)
            cartItems.add(itemChanged);
        else
            cartItems.remove(itemChanged);

        cartRecyclerViewAdapter.notifyDataSetChanged();

        updateSummaryDisplay();
    }

    /**
     * This is used to update the list of summary prices displays
     */
    private void updateSummaryDisplay() {
        summaryList[0].setValue(storeManager.getSubtotal());
        summaryList[1].setValue(0.0);
        summaryList[2].setValue(storeManager.getSalesTax());
        summaryList[3].setValue(storeManager.getTotal());

        summaryListViewAdapter.notifyDataSetChanged();
    }
}