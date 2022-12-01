package edu.rutgers.rupizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.rutgers.rupizzeria.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.recyclerviews.holders.CrustViewHolder;
import edu.rutgers.rupizzeria.recyclerviews.holders.FlavorsViewHolder;
import edu.rutgers.rupizzeria.recyclerviews.holders.SizeViewHolder;
import edu.rutgers.rupizzeria.recyclerviews.holders.ToppingsViewHolder;
import edu.rutgers.rupizzeria.types.Crust;
import edu.rutgers.rupizzeria.types.Size;
import edu.rutgers.rupizzeria.types.Topping;
import edu.rutgers.rupizzeria.ui.home.FlavorItem;

public class OrderFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);

        // Choose Size
        RecyclerView chooseSizeRecyclerView = findViewById(R.id.order_food_choose_size_recycler_view);
        List<Size> sizes = new ArrayList<>();

        sizes.addAll(Arrays.asList(Size.values()));

        GenericRecyclerViewAdapter<Size, SizeViewHolder> chooseSizeRecyclerViewAdapter =
                new GenericRecyclerViewAdapter<>(sizes, this, SizeViewHolder.class, R.layout.recycler_view_radio_button_item);

        chooseSizeRecyclerView.setAdapter(chooseSizeRecyclerViewAdapter);
        chooseSizeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Choose Crust
        RecyclerView chooseCrustRecyclerView = findViewById(R.id.order_food_choose_crust_recycler_view);
        List<Crust> crusts = new ArrayList<>();

        crusts.addAll(Arrays.asList(Crust.values()));

        GenericRecyclerViewAdapter<Crust, CrustViewHolder> crustRecyclerViewAdapter =
                new GenericRecyclerViewAdapter<>(crusts, this, CrustViewHolder.class, R.layout.recycler_view_radio_button_item);

        chooseCrustRecyclerView.setAdapter(crustRecyclerViewAdapter);
        chooseCrustRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Select Toppings
        RecyclerView selectToppingsRecyclerView = findViewById(R.id.order_food_select_toppings_recycler_view);
        List<Topping> toppings = new ArrayList<>();

        toppings.addAll(Arrays.asList(Topping.values()));

        GenericRecyclerViewAdapter<Topping, ToppingsViewHolder> toppingsRecyclerViewAdapter =
                new GenericRecyclerViewAdapter<>(toppings, this, ToppingsViewHolder.class, R.layout.recycler_view_checkbox_item);

        selectToppingsRecyclerView.setAdapter(toppingsRecyclerViewAdapter);
        selectToppingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
