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
import java.util.Arrays;
import java.util.List;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericRecyclerViewAdapter;
import edu.rutgers.rupizzeria.client.recyclerviews.holders.FoodsViewHolder;
import edu.rutgers.rupizzeria.databinding.FragmentHomeBinding;
import edu.rutgers.rupizzeria.main.core.types.Style;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = root.getContext();

        // Setting up the recycler view
        RecyclerView foodsRecyclerView = root.findViewById(R.id.foods_recycler_view);

        List<Style> foods = new ArrayList<>(Arrays.asList(Style.values()));

        GenericRecyclerViewAdapter<Style, FoodsViewHolder> foodsRecyclerViewAdapter =
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