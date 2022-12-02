package edu.rutgers.rupizzeria.recyclerviews.holders;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Map;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.ui.home.KeyValueItem;
import kotlin.Unit;

public class KeyValueViewHolder extends GenericViewHolder<KeyValueItem<String, String>> {

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
    public void onBind(KeyValueItem<String, String> entry) {
        keyText.setText(entry.getKey());
        valueText.setText(entry.getValue());

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
