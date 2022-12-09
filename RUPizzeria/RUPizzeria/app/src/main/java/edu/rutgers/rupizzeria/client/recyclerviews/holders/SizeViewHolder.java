package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.utils.Utils;

/**
 * ViewHolder for the size recyclerview which
 * lists the available sizes for the user to choose
 * @author Michael Liu, Genfu Liu
 */
public class SizeViewHolder extends GenericViewHolder<Size> {

    /**
     * This static variable controls which RadioButton is selected in the group
     * Since we're not using a RadioGroup we have to manage this ourselves
     * (RadioButtons are list items in a RecyclerView so we can't use RadioGroup)
     */
    public static RadioButton currentlySelected;

    /**
     * TextView to display the size
     */
    private TextView sizeText;

    /**
     * RadioButton for the user to select this size
     */
    private RadioButton sizeRadioButton;

    /**
     * Constructor to find all the corresponding view elements and initializing the fields
     * @param itemView Current list item's view to pass into the parent's constructor
     */
    public SizeViewHolder(@NonNull View itemView) {
        super(itemView);

        sizeText = itemView.findViewById(R.id.recycler_item_radio_text);
        sizeRadioButton = itemView.findViewById(R.id.recycler_item_radio_button);
    }

    /**
     * Overridden onBind method to initialize all the view elements
     * when they're created with their data
     * @param size The current size of the food
     */
    @Override
    public void onBind(Size size) {
        sizeText.setText(Utils.capitalize(size.toString()));

        sizeRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> onChooseSize(size, isChecked));

        if (size == Size.SMALL)
            sizeRadioButton.setChecked(true);
    }

    /**
     * This is called when the user click on the radiobutton to choose a speific size
     * @param size the size that the user chooses
     * @param isChecked if the radio button is checked or unchecked (user selects a different button)
     */
    public void onChooseSize(Size size, boolean isChecked) {
        if (isChecked)
            StoreManager.getInstance().setCurrentItemSize(size);

        if (currentlySelected != null)
            currentlySelected.setChecked(false);

        currentlySelected = sizeRadioButton;
    }
}
