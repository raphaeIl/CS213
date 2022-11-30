package edu.rutgers.rupizzeria.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.rutgers.rupizzeria.OrderFoodActivity;
import edu.rutgers.rupizzeria.R;

public class FoodsRecyclerViewAdapter extends RecyclerView.Adapter<FoodsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<FoodItem> foodsList;
    private Context context;

    public FoodsRecyclerViewAdapter(Context context) {
        foodsList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.foodImage.setImageResource(foodsList.get(position).getFoodImageResId());
        holder.foodInfoText.setText(foodsList.get(position).getFoodInfo());

        holder.parent.setOnClickListener(v -> onSelectFood(foodsList.get(position)));

    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }

    public void onSelectFood(FoodItem foodItem) {
        Toast.makeText(context, foodItem.getFoodInfo(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, OrderFoodActivity.class);

        intent.putExtra("foodName", foodItem.getFoodName());
        this.context.startActivity(intent);
    }


    public void setFoodsList(ArrayList<FoodItem> foodsList) {
        this.foodsList = foodsList;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView foodImage;
        private TextView foodNameText;
        private TextView foodInfoText;

        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.recycler_item_food_image);
            foodInfoText = itemView.findViewById(R.id.recycler_item_food_info_text);
            parent = itemView.findViewById(R.id.foods_recycler_view_item_parent);
        }
    }

}
