package edu.rutgers.rupizzeria.recyclerviews.holders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.types.Crust;
import edu.rutgers.rupizzeria.utils.Utils;

public class CrustViewHolder extends GenericViewHolder<Crust> {

    private TextView crustText;
    private RadioButton crustRadioButton;

    public CrustViewHolder(@NonNull View itemView) {
        super(itemView);

        crustText = itemView.findViewById(R.id.recycler_item_radio_text);
        crustRadioButton = itemView.findViewById(R.id.recycler_item_radio_button);
    }

    @Override
    public void onBind(Crust crust) {
        crustText.setText(Utils.capitalize(crust.toString()));

        crustRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> onChooseCrust(crust));
    }

    public void onChooseCrust(Crust crust) {
        Toast.makeText(currentContext, crust.toString(), Toast.LENGTH_SHORT).show();
    }
}
