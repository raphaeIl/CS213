package edu.rutgers.rupizzeria.recyclerviews.holders;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.types.Size;
import edu.rutgers.rupizzeria.utils.Utils;

public class SizeViewHolder extends GenericViewHolder<Size> {

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

        sizeRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> onChooseSize(size));
    }

    public void onChooseSize(Size size) {
        Toast.makeText(currentContext, size.toString(), Toast.LENGTH_SHORT).show();
    }
}
