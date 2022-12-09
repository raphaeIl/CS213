package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Crust;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.utils.Utils;

/**
 * ViewHolder for the crusts recyclerview which
 * lists the available crust for the user to choose
 * @author Michael Liu, Genfu Liu
 */
public class CrustViewHolder extends GenericViewHolder<Crust> {

    /**
     * TextView to display the crust type
     */
    private TextView crustText;

    /**
     * RadioButton for the user to choose this type of crust
     * (In this app all crusts are pre-chosen)
     */
    private RadioButton crustRadioButton;

    /**
     * Constructor to find all the corresponding view elements and initializing the fields
     * @param itemView Current list item's view to pass into the parent's constructor
     */
    public CrustViewHolder(@NonNull View itemView) {
        super(itemView);

        crustText = itemView.findViewById(R.id.recycler_item_radio_text);
        crustRadioButton = itemView.findViewById(R.id.recycler_item_radio_button);
    }

    /**
     * Overridden onBind method to initialize all the view elements
     * when they're created with their data
     * @param crust The current crust
     */
    @Override
    public void onBind(Crust crust) {
        crustText.setText(Utils.capitalize(crust.toString()));

        crustRadioButton.setEnabled(false);

        if (StoreManager.getInstance().getCurrentItem().getCrust() == crust)
            crustRadioButton.setChecked(true);
    }
}
