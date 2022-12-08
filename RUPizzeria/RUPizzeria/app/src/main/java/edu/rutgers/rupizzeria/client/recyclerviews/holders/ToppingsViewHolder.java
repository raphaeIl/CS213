package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.os.Build;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Flavor;
import edu.rutgers.rupizzeria.main.core.types.Topping;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
import edu.rutgers.rupizzeria.utils.Logger;
import edu.rutgers.rupizzeria.utils.Utils;

public class ToppingsViewHolder extends GenericViewHolder<Topping> {

    private TextView toppingText;
    private CheckBox toppingCheckBox;

    public ToppingsViewHolder(@NonNull View itemView) {
        super(itemView);

        toppingText = itemView.findViewById(R.id.recycler_item_checkbox_text);
        toppingCheckBox = itemView.findViewById(R.id.recycler_item_checkbox);
    }

    @Override
    public void onBind(Topping topping) {
        toppingText.setText(Utils.capitalize(topping.toString()));

        toppingCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> onSelectTopping(topping, isChecked));

        Pizza currentItem = StoreManager.getInstance().getCurrentItem();

        if (currentItem.getFlavor() != Flavor.Build_Your_Own)
            toppingCheckBox.setEnabled(false);

        if (currentItem.getToppings().contains(topping)) {
            toppingCheckBox.setChecked(true);
        }

    }

    public void onSelectTopping(Topping topping, boolean isSelected) {
        int totalToppingsCount = StoreManager.getInstance().getCurrentItem().getToppings().size();

        if (isSelected && totalToppingsCount >= 7) {
            toppingCheckBox.setChecked(false);
            return;
        }

        if (isSelected)
            StoreManager.getInstance().addToppingToCurrentItem(topping);
        else
            StoreManager.getInstance().removeToppingFromCurrentItem(topping);
    }
}
