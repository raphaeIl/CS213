package edu.rutgers.rupizzeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.rutgers.rupizzeria.client.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.CrustViewHolder;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.SizeViewHolder;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.ToppingsViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Crust;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Topping;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Logger;
import edu.rutgers.rupizzeria.utils.Utils;

/**
 * This activity corresponds to the order food page of the application
 * allows the user to choose the size, view the crust, and the toppings of the pizza
 * as well as adding it to the cart.
 * @author Michael Liu, Genfu Liu
 */
public class OrderFoodActivity extends AppCompatActivity {

    /**
     * Inherited onCreate method
     * Another really long and ugly method to set up each of the recycler views
     * for size, crust and topping selection lists.
     * @param savedInstanceState inherited param
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        setTitle(R.string.title_order_food);
        Pizza currentFood = StoreManager.getInstance().getCurrentItem();

        ((ImageView)findViewById(R.id.order_food_image)).setImageResource(Utils.getPizzaImage(currentFood.getStyle(), currentFood.getFlavor()));
        ((TextView)findViewById(R.id.order_food_name)).setText(currentFood.getFlavor().toString());
        ((TextView)findViewById(R.id.order_food_price)).setText(Utils.formatCurrency(currentFood.price()));

        // Choose Size
        RecyclerView chooseSizeRecyclerView = findViewById(R.id.order_food_choose_size_recycler_view);
        List<Size> sizes = new ArrayList<>(Arrays.asList(Size.values()));
        GenericRecyclerViewAdapter<Size, SizeViewHolder> chooseSizeRecyclerViewAdapter = new GenericRecyclerViewAdapter<>(sizes, this, SizeViewHolder.class, R.layout.recycler_view_radio_button_item);

        chooseSizeRecyclerView.setAdapter(chooseSizeRecyclerViewAdapter);
        chooseSizeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Choose Crust
        RecyclerView chooseCrustRecyclerView = findViewById(R.id.order_food_choose_crust_recycler_view);
        List<Crust> crusts = new ArrayList<>(Arrays.asList(Crust.values()));
        GenericRecyclerViewAdapter<Crust, CrustViewHolder> crustRecyclerViewAdapter = new GenericRecyclerViewAdapter<>(crusts, this, CrustViewHolder.class, R.layout.recycler_view_radio_button_item);

        chooseCrustRecyclerView.setAdapter(crustRecyclerViewAdapter);
        chooseCrustRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Select Toppings
        RecyclerView selectToppingsRecyclerView = findViewById(R.id.order_food_select_toppings_recycler_view);
        List<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.values()));
        GenericRecyclerViewAdapter<Topping, ToppingsViewHolder> toppingsRecyclerViewAdapter = new GenericRecyclerViewAdapter<>(toppings, this, ToppingsViewHolder.class, R.layout.recycler_view_checkbox_item);

        selectToppingsRecyclerView.setAdapter(toppingsRecyclerViewAdapter);
        selectToppingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.order_food_add_to_cart_button).setOnClickListener(v -> onAddToCart());

        StoreManager.getInstance().addCurrentItemChangedListener((itemChanged, isRemoved) -> {
            ((TextView)findViewById(R.id.order_food_price)).setText(Utils.formatCurrency(itemChanged.price()));
            ((Button)findViewById(R.id.order_food_add_to_cart_button)).setText(String.format("Add 1 to cart • US %s", Utils.formatCurrency(itemChanged.price())));
        });
    }

    /**
     * Adds the current order to the cart,
     * called when the add_to_cart button is clicked
     */
    public void onAddToCart() {
        if (StoreManager.getInstance().getCurrentItem().getToppings().size() <= 0) {
            Toast.makeText(this, "Please select your toppings.", Toast.LENGTH_SHORT).show();
            return;
        }

        DialogInterface.OnClickListener onConfirmAddToCart = (dialog, which) -> {
            StoreManager.getInstance().addCurrentItemToCart();
            Toast.makeText(this, "Item successfully added!", Toast.LENGTH_SHORT).show();

            finish();
        };

        DialogInterface.OnClickListener onCancelAddToCart = (dialog, which) -> dialog.cancel();

        Logger.logAlertConfirmation(this, "Add To Cart", "Are you sure you want to add this item to the cart?", onConfirmAddToCart, onCancelAddToCart);
    }

    /**
     * This overridden method is called when the back arrow in the top options menu is clicked
     * looks like an empty method but if we didn't override the method the page somehow crashes
     * @param item inherited MenuItem param
     * @return if the item is successfully selected
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}
