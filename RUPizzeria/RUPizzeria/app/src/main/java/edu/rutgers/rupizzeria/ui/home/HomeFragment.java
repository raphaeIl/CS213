package edu.rutgers.rupizzeria.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = root.getContext();

        RecyclerView foodsRecyclerView = root.findViewById(R.id.foods_recycler_view);
//        FoodsRecyclerViewAdapter adapter = new FoodsRecyclerViewAdapter(context);

        List<FoodItem> foods = new ArrayList<>();
        foods.add(new FoodItem(R.drawable.main_menu_chicago_style, "\uD83C\uDF55 Chicago Style Pizza", "0 min • $12.99 • $0 Delivery fee"));
        foods.add(new FoodItem(R.drawable.main_menu_ny_style, "\uD83C\uDF55 NY Style Pizza", "0 min • $17.99 • $0 Delivery fee"));

        GenericRecyclerViewAdapter<FoodItem, FoodsViewHolder> adapter =
                new GenericRecyclerViewAdapter<>(foods, context, FoodsViewHolder.class, R.layout.foods_recycler_view_item);

//        adapter.setFoodsList(foods);
        foodsRecyclerView.setAdapter(adapter);
        foodsRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class FoodsViewHolder extends GenericViewHolder<FoodItem> {

        private ImageView foodImage;
        private TextView foodNameText;
        private TextView foodInfoText;

        private CardView parent;

        public FoodsViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.recycler_item_food_image);
            foodInfoText = itemView.findViewById(R.id.recycler_item_food_info_text);
            foodNameText = itemView.findViewById(R.id.recycler_item_food_name_text);
            parent = itemView.findViewById(R.id.foods_recycler_view_item_parent);
        }

        @Override
        public void onBind(FoodItem foodItem) {
            foodImage.setImageResource(foodItem.getFoodImageResId());
            foodInfoText.setText(foodItem.getFoodInfo());
            foodNameText.setText(foodItem.getFoodName());

            Log.d("FoodsRecyclerViewAdap", "position: " + foodItem.getFoodName());

//            holder.parent.setOnClickListener(v -> onSelectFood(foodsList.get(position)));
        }
    }
}