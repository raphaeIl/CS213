package edu.rutgers.rupizzeria.recyclerviews.holders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.types.Topping;
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

        toppingCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> onSelectTopping(topping));
    }

    public void onSelectTopping(Topping topping) {
        Toast.makeText(currentContext, topping.toString(), Toast.LENGTH_SHORT).show();
    }
}
