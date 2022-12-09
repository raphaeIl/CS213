package edu.rutgers.rupizzeria.client.ui.cart;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.utils.Utils;

/**
 * Custom ViewHolder for the summary ListView in the cart view
 * which stores all the summary prices
 * @author Michael Liu, Genfu Liu
 */
public class SummaryViewHolder {

    /**
     * TextView to display the name of the price
     */
    private TextView keyText;

    /**
     * TextView to display the price's value
     */
    private TextView valueText;

    /**
     * Parent of the view holder item
     */
    private RelativeLayout parent;

    /**
     * Constructor to find all the corresponding view elements and initializing the fields
     * @param itemView Current list item's view to pass into the parent's constructor
     */
    public SummaryViewHolder(@NonNull View itemView) {
        keyText = itemView.findViewById(R.id.list_item_key);
        valueText = itemView.findViewById(R.id.list_item_value);

        parent = itemView.findViewById(R.id.list_key_value_item_parent);
    }

    /**
     * Custom onBind method to initialize all the view elements
     * when they're created with their data
     * @param entry The KeyValue pair this holder view is storing
     */
    public void onBind(KeyValueItem<String, Double> entry) {
        keyText.setText(entry.getKey());
        valueText.setText(Utils.formatCurrency(entry.getValue())); // hard coded

        switch (entry.getSize()) {
            case SMALL:
                keyText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                valueText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                break;
            case LARGE:
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
