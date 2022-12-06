package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.client.ui.home.KeyValueItem;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.utils.Utils;

public class KeyValueViewHolder extends GenericViewHolder<KeyValueItem<String, Double>> {

    private TextView keyText;
    private TextView valueText;

    private RelativeLayout parent;

    public KeyValueViewHolder(@NonNull View itemView) {
        super(itemView);

        keyText = itemView.findViewById(R.id.recycler_item_key);
        valueText = itemView.findViewById(R.id.recycler_item_value);

        parent = itemView.findViewById(R.id.recycler_key_value_item_parent);
    }

    @Override
    public void onBind(KeyValueItem<String, Double> entry) {
        keyText.setText(entry.getKey());
        valueText.setText(Utils.formatCurrency(entry.getValue())); // hard coded

        switch (entry.getSize()) { // hardcoded for now
            case SMALL:
                keyText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                valueText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                break;
            case LARGE:
                ((ViewGroup.MarginLayoutParams)parent.getLayoutParams()).topMargin = 20;
                keyText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                keyText.setTypeface(Typeface.DEFAULT_BOLD);
                valueText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                valueText.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            default:
                break;
        }

    }
}
