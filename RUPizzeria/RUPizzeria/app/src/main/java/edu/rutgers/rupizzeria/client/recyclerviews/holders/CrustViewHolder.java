package edu.rutgers.rupizzeria.client.recyclerviews.holders;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import edu.rutgers.rupizzeria.R;
import edu.rutgers.rupizzeria.client.recyclerviews.GenericViewHolder;
import edu.rutgers.rupizzeria.main.core.types.Crust;
import edu.rutgers.rupizzeria.main.managers.StoreManager;
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

        crustRadioButton.setEnabled(false);

        if (StoreManager.getInstance().getCurrentItem().getCrust() == crust)
            crustRadioButton.setChecked(true);
    }
}
