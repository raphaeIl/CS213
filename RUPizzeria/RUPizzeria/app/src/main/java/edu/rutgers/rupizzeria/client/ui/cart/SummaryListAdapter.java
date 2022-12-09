package edu.rutgers.rupizzeria.client.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edu.rutgers.rupizzeria.R;

/**
 * Custom Adapter for the Summary ListView
 * @author Michael Liu, Genfu Liu
 */
public class SummaryListAdapter extends ArrayAdapter<KeyValueItem<String, Double>> {

    /**
     * List of the items that this recycler view is storing
     */
    private final KeyValueItem<String, Double>[] summaryList;

    /**
     * The context that this recycler view is in
     */
    private final Context context;

    /**
     *Constructor to initialize all the fields
     * @param context the current context
     * @param resource the resource id to pass to the super class
     * @param objects array of item that this listview is holding
     */
    public SummaryListAdapter(@NonNull Context context, int resource, @NonNull KeyValueItem<String, Double>[] objects) {
        super(context, resource, objects);

        this.summaryList = objects;
        this.context = context;
    }

    /**
     * Overridden method that is called when the view is created
     * @param position position of this item in the list
     * @param convertView the current item's view
     * @param parent the parent view
     * @return the initialized current item's view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.list_view_key_value_item, null);

        SummaryViewHolder summaryViewHolder = new SummaryViewHolder(convertView);
        summaryViewHolder.onBind(getItem(position));

        return convertView;
    }
}
