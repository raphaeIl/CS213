package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.rutgers.rupizzeria.OrderFoodActivity;
import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.client.ui.home.FlavorItem;
import edu.rutgers.rupizzeria.main.core.customizable.Order;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Style;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Utils;

public class FlavorsViewHolder extends GenericViewHolder<FlavorItem> {

    private ImageView flavorImage;

    private TextView flavorNameText;
    private TextView flavorPriceText;
    private TextView flavorDescriptionText;

    private ConstraintLayout parent;

    public FlavorsViewHolder(@NonNull View itemView) {
        super(itemView);

        flavorImage = itemView.findViewById(R.id.select_flavor_list_image);
        flavorNameText = itemView.findViewById(R.id.cart_item_name);
        flavorPriceText = itemView.findViewById(R.id.cart_item_price);
        flavorDescriptionText = itemView.findViewById(R.id.cart_item_description);

        parent = itemView.findViewById(R.id.select_flavor_item_parent);
    }

    @Override
    public void onBind(FlavorItem flavorItem) {
        flavorImage.setImageResource(flavorItem.getFlavorImageResId());
        flavorNameText.setText(flavorItem.getFlavor() + " Pizza");
        flavorPriceText.setText(flavorItem.getFlavorPrice());
        flavorDescriptionText.setText(flavorItem.getFlavorDescription());

        parent.setOnClickListener(v -> onSelectFlavor(flavorItem));
    }

    public void onSelectFlavor(FlavorItem flavorItem) {
        Toast.makeText(currentContext, flavorItem.getFlavor().toString(), Toast.LENGTH_SHORT).show();

        StoreManager.getInstance().selectPizza(Style.Chicago, flavorItem.getFlavor(), Size.SMALL); // default size of small

        // next activity
        Intent intent = new Intent(this.currentContext, OrderFoodActivity.class);

        this.currentContext.startActivity(intent);
    }
}
