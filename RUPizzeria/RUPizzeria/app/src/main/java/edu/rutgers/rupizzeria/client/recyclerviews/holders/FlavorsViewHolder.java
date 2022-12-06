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
import edu.rutgers.rupizzeria.SelectFlavorActivity;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.core.customizable.Order;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.core.types.Style;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Utils;

public class FlavorsViewHolder extends GenericViewHolder<Flavor> {

    private ImageView flavorImage;

    private TextView flavorNameText;
    private TextView flavorPriceText;
    private TextView flavorDescriptionText;

    private ConstraintLayout parent;

    private Style currentStyle;

    public FlavorsViewHolder(@NonNull View itemView) {
        super(itemView);

        flavorImage = itemView.findViewById(R.id.select_flavor_list_image);
        flavorNameText = itemView.findViewById(R.id.cart_item_name);
        flavorPriceText = itemView.findViewById(R.id.cart_item_price);
        flavorDescriptionText = itemView.findViewById(R.id.cart_item_description);

        parent = itemView.findViewById(R.id.select_flavor_item_parent);
    }

    @Override
    public void onBind(Flavor flavor) {
        currentStyle = (Style) ((SelectFlavorActivity)currentContext).getIntent().getSerializableExtra("food_style");

        flavorImage.setImageResource(Utils.getPizzaImage(currentStyle, flavor));
        flavorNameText.setText(flavor + " Pizza");
        flavorPriceText.setText(Utils.formatCurrency(0.00));
        flavorDescriptionText.setText(String.format("A %s Style %s Pizza", currentStyle, flavor));

        parent.setOnClickListener(v -> onSelectFlavor(flavor));
    }

    public void onSelectFlavor(Flavor flavor) {
        Toast.makeText(currentContext, flavor.toString(), Toast.LENGTH_SHORT).show();

        StoreManager.getInstance().selectPizza(currentStyle, flavor, Size.SMALL);

        // next activity
        Intent intent = new Intent(this.currentContext, OrderFoodActivity.class);
        this.currentContext.startActivity(intent);
    }
}
