package edu.rutgers.rupizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.rutgers.rupizzeria.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.recyclerviews.holders.FlavorsViewHolder;
import edu.rutgers.rupizzeria.ui.home.FlavorItem;

public class SelectFlavorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flavor);

        Intent intent = getIntent();

        // Setting up the recycler view
        RecyclerView flavorsRecyclerView = findViewById(R.id.select_flavor_recycler_view);
        List<FlavorItem> flavors = new ArrayList<>();

        flavors.add(new FlavorItem(R.drawable.chicago_bbq_chicken, "BBQ Chicken Pizza", "$13.99", "A Chicago Style BBQ Chicken Pizza")); // We had you at bacon, right? It gets even better. Smoky Southern-style BBQ sauce adds a tangy twist to this irresistible pizza topped with grilled chicken, hickory-smoked bacon, fresh-cut onions, and real cheese made from mozzarella. Round one up today.
        flavors.add(new FlavorItem(R.drawable.chicago_deluxe, "Deluxe Pizza", "$14.99", "A Chicago Style Deluxe Pizza")); // Your choice of crust covered with our signature pizza sauce, real cheese made from mozzarella, and pepperoni. With a pepperoni in almost every bite, it's one of our most popular pizzas.
        flavors.add(new FlavorItem(R.drawable.chicago_meatzza, "Meatzza Pizza", "$15.99", "A Chicago Style Meatzza Pizza")); // A masterpiece of hearty, high-quality meats including pepperoni, savory sausage, real beef, hickory-smoked bacon, and julienne-cut Canadian bacon, all topped with real cheese made from mozzarella.
        flavors.add(new FlavorItem(R.drawable.build_your_own, "Build your own Pizza", "$8.99", "A Chicago Style Build your own Pizza")); // Build your own custom pizza and we'll handcraft it for you. With so many delicious options to choose from, your masterpiece is waiting!"

        GenericRecyclerViewAdapter<FlavorItem, FlavorsViewHolder> foodsRecyclerViewAdapter =
                new GenericRecyclerViewAdapter<>(flavors, this, FlavorsViewHolder.class, R.layout.select_flavor_recycler_view_item);

        flavorsRecyclerView.setAdapter(foodsRecyclerViewAdapter);
        flavorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Setup done
    }
}