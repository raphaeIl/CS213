package edu.rutgers.rupizzeria.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

// A generic ViewAdapter that can be used for any recycler view paired with a generic ViewHolder
// without this class, we would have to create a custom class for ViewAdapter and a ViewHolder everytime we use a RecyclerView
public class GenericRecyclerViewAdapter<T, VH extends GenericViewHolder<T>> extends RecyclerView.Adapter<VH> {

    private final List<T> itemsList;
    private final Context context;
    private final int viewLayoutID;

    private final Class<VH> viewHolderType;

    public GenericRecyclerViewAdapter(List<T> itemsList, Context context, Class<VH> viewHolderType, int viewLayoutID) {
        this.itemsList = itemsList;
        this.context = context;
        this.viewLayoutID = viewLayoutID;

        this.viewHolderType = viewHolderType;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.viewLayoutID, parent, false);

        try {// this is basically doing new VH(); but you can not instantiate a generic class in java so we had to do this, stackoverflow reference: https://stackoverflow.com/questions/75175/create-instance-of-generic-type-in-java
            return (VH) this.viewHolderType.getConstructors()[0].newInstance(view);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.onBind(itemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public List<T> getItemsList() {
        notifyDataSetChanged();

        return itemsList;
    }
}
