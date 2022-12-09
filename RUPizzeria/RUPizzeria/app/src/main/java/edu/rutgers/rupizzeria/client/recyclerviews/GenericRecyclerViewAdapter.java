package edu.rutgers.rupizzeria.client.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * A generic ViewAdapter that can be used for any recycler view paired with a generic ViewHolder
 * without this class, we would have to create a custom class for ViewAdapter and a ViewHolder everytime we use a RecyclerView
 * Subclass of the original RecyclerView.Adapter parent class
 * @param <T> The type of items that this recyclerview list is storing
 * @param <VH> The ViewHolder class type
 * @author Michael Liu, Genfu Liu
 */
public class GenericRecyclerViewAdapter<T, VH extends GenericViewHolder<T>> extends RecyclerView.Adapter<VH> {

    /**
     * List of the items that this recycler view is storing
     */
    private final List<T> itemsList;

    /**
     * The context that this recycler view is in
     */
    private final Context context;

    /**
     * The layout id of the specific view holder item of the recycler view
     */
    private final int viewHolderLayoutID;

    /**
     * The Class Type of the ViewHolder, useful in onCreateViewHolder
     */
    private final Class<VH> viewHolderType;

    /**
     * Constructor to initialize all the fields
     * @param itemsList the list of the type items to be stored
     * @param context the current context
     * @param viewHolderType the class style of the viewholder
     * @param viewLayoutID the layout id of the viewholder item layout
     */
    public GenericRecyclerViewAdapter(List<T> itemsList, Context context, Class<VH> viewHolderType, int viewLayoutID) {
        this.itemsList = itemsList;
        this.context = context;
        this.viewHolderLayoutID = viewLayoutID;

        this.viewHolderType = viewHolderType;
    }

    /**
     * Overridden method that inflates creates viewholder
     * @param parent Inherited param, the recyclerview
     * @param viewType Inherited param, the view type
     * @return
     */
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.viewHolderLayoutID, parent, false);

        try {// this is basically doing new VH(); but you can not instantiate a generic class in java so we had to do this, stackoverflow reference: https://stackoverflow.com/questions/75175/create-instance-of-generic-type-in-java
            return (VH) this.viewHolderType.getConstructors()[0].newInstance(view);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Overridden method that is called whne the viewholder is binded
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.onBind(itemsList.get(position));
    }

    /**
     * Gets the total item count of the itemList
     * @return the item count
     */
    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
