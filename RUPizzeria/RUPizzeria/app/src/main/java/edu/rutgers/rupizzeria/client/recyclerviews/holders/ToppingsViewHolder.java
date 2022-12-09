package edu.rutgers.rupizzeria.client.recyclerviews.holders;

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
import edu.rutgers.rupizzeria.utils.Utils;

/**
 * ViewHolder for the toppings recyclerview which
 * lists the available toppings for the user to choose
 * @author Michael Liu, Genfu Liu
 */
public class ToppingsViewHolder extends GenericViewHolder<Topping> {

    /**
     * TextView to display the toppings name
     */
    private TextView toppingText;

    /**
     * CheckBox for the user to select this topping
     */
    private CheckBox toppingCheckBox;

    /**
     * Constructor to find all the corresponding view elements and initializing the fields
     * @param itemView Current list item's view to pass into the parent's constructor
     */
    public ToppingsViewHolder(@NonNull View itemView) {
        super(itemView);

        toppingText = itemView.findViewById(R.id.recycler_item_checkbox_text);
        toppingCheckBox = itemView.findViewById(R.id.recycler_item_checkbox);
    }

    /**
     * Overridden onBind method to initialize all the view elements
     * when they're created with their data
     * @param topping The topping that this viewholder binds to
     */
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

    /**
     * This is called when the user selects the checkbox to select the current topping
     * @param topping The topping that the user selects
     * @param isSelected If the checkbox is selected or deselected
     */
    public void onSelectTopping(Topping topping, boolean isSelected) {
        int totalToppingsCount = StoreManager.getInstance().getCurrentItem().getToppings().size();

        if (isSelected && totalToppingsCount >= 7) {
            toppingCheckBox.setChecked(false);

            Toast.makeText(currentContext, "You can only select a maximum of 7 toppings!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isSelected)
            StoreManager.getInstance().addToppingToCurrentItem(topping);
        else
            StoreManager.getInstance().removeToppingFromCurrentItem(topping);
    }
}
