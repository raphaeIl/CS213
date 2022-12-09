package edu.rutgers.rupizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.rutgers.rupizzeria.client.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.FlavorsViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Style;

/**
 * This activity corresponds to the select flavor page of the application
 * responsible for letting the user choose their desired pizza flavor
 * @author Michael Liu, Genfu Liu
 */
public class SelectFlavorActivity extends AppCompatActivity {

    /**
     * This stores the current style of the pizza, Chicago or NY
     * which is selected by the user in the previous main activity
     */
    public static Style currentStyle;

    /**
     * Inherited onCreate method
     *  Sets up the recycler view which displays all the flavors in a list
     * @param savedInstanceState inherited param
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flavor);
        setTitle(R.string.title_order_food);

        currentStyle = (Style) getIntent().getSerializableExtra("food_style"); // getting the user's style option from the info passed into the intent

        ((ImageView)findViewById(R.id.select_flavor_image)).setImageResource(currentStyle == Style.Chicago ? R.drawable.main_menu_chicago_style : R.drawable.main_menu_ny_style);
        ((TextView)findViewById(R.id.select_flavor_name)).setText(String.format("%s Style Pizza", currentStyle));

        // Setting up the recycler view
        RecyclerView flavorsRecyclerView = findViewById(R.id.select_flavor_recycler_view);
        List<Flavor> flavors = new ArrayList<>(Arrays.asList(Flavor.values()));
        // no price

        GenericRecyclerViewAdapter<Flavor, FlavorsViewHolder> foodsRecyclerViewAdapter =
                new GenericRecyclerViewAdapter<>(flavors, this, FlavorsViewHolder.class, R.layout.select_flavor_recycler_view_item);

        flavorsRecyclerView.setAdapter(foodsRecyclerViewAdapter);
        flavorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Setup done
    }
}