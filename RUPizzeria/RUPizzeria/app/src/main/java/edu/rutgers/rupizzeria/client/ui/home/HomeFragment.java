package edu.rutgers.rupizzeria.client.ui.home;

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

import java.util.ArrayList;
import java.util.List;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.FoodsViewHolder;
import edu.rutgers.rupizzeria.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = root.getContext();

        // Setting up the recycler view
        RecyclerView foodsRecyclerView = root.findViewById(R.id.foods_recycler_view);
        List<FoodItem> foods = new ArrayList<>();

        foods.add(new FoodItem(R.drawable.main_menu_chicago_style, "\uD83C\uDF55 Chicago Style Pizza", "0 min • $12.99 • $0 Delivery fee"));
        foods.add(new FoodItem(R.drawable.main_menu_ny_style, "\uD83C\uDF55 NY Style Pizza", "0 min • $17.99 • $0 Delivery fee"));

        GenericRecyclerViewAdapter<FoodItem, FoodsViewHolder> foodsRecyclerViewAdapter =
                new GenericRecyclerViewAdapter<>(foods, context, FoodsViewHolder.class, R.layout.foods_recycler_view_item);

        foodsRecyclerView.setAdapter(foodsRecyclerViewAdapter);
        foodsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        // Setup done


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}