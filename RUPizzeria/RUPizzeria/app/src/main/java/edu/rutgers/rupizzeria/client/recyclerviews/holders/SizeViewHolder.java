package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Size;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
import edu.rutgers.rupizzeria.utils.Utils;

public class SizeViewHolder extends GenericViewHolder<Size> {

    public static RadioButton currentlySelected;

    private TextView sizeText;
    private RadioButton sizeRadioButton;

    public SizeViewHolder(@NonNull View itemView) {
        super(itemView);

        sizeText = itemView.findViewById(R.id.recycler_item_radio_text);
        sizeRadioButton = itemView.findViewById(R.id.recycler_item_radio_button);
    }

    @Override
    public void onBind(Size size) {
        sizeText.setText(Utils.capitalize(size.toString()));

        sizeRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> onChooseSize(size, isChecked));

        if (size == Size.SMALL)
            sizeRadioButton.setChecked(true);
    }

    public void onChooseSize(Size size, boolean isChecked) {
        if (isChecked)
            StoreManager.getInstance().setCurrentItemSize(size);

        if (currentlySelected != null)
            currentlySelected.setChecked(false);

        currentlySelected = sizeRadioButton;
    }
}
