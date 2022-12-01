package edu.rutgers.rupizzeria.recyclerviews.holders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.rutgers.rupizzeria.OrderFoodActivity;
import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.SelectFlavorActivity;
import edu.rutgers.rupizzeria.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.ui.home.FlavorItem;

public class FlavorsViewHolder extends GenericViewHolder<FlavorItem> {

    private ImageView flavorImage;

    private TextView flavorNameText;
    private TextView flavorPriceText;
    private TextView flavorDescriptionText;

    private ConstraintLayout parent;

    public FlavorsViewHolder(@NonNull View itemView) {
        super(itemView);

        flavorImage = itemView.findViewById(R.id.select_flavor_list_image);
        flavorNameText = itemView.findViewById(R.id.select_flavor_list_name);
        flavorPriceText = itemView.findViewById(R.id.select_flavor_list_price);
        flavorDescriptionText = itemView.findViewById(R.id.select_flavor_list_description);

        parent = itemView.findViewById(R.id.select_flavor_item_parent);
    }

    @Override
    public void onBind(FlavorItem flavorItem) {
        flavorImage.setImageResource(flavorItem.getFlavorImageResId());
        flavorNameText.setText(flavorItem.getFlavorName());
        flavorPriceText.setText(flavorItem.getFlavorPrice());
        flavorDescriptionText.setText(flavorItem.getFlavorDescription());

        parent.setOnClickListener(v -> onSelectFlavor(flavorItem));

    }

    public void onSelectFlavor(FlavorItem flavorItem) {
        Toast.makeText(currentContext, flavorItem.getFlavorName(), Toast.LENGTH_SHORT).show();

        // next activity
        Intent intent = new Intent(this.currentContext, OrderFoodActivity.class);

        this.currentContext.startActivity(intent);
    }
}