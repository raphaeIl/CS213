package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Topping;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.main.pizzafactory.Pizza;
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
    }

    public void onSelectTopping(Topping topping, boolean isSelected) {
        Toast.makeText(currentContext, topping.toString(), Toast.LENGTH_SHORT).show();

        Pizza currentItem = StoreManager.getInstance().getCurrentItem();

        if (isSelected)
            currentItem.add(topping);
        else
            currentItem.remove(topping);
    }
}
