package edu.rutgers.rupizzeria.client.recyclerviews;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A generic ViewHolder that stores T type items,
 * need to be extended for different recyclerviews and its holder
 * Subclass of the original RecyclerView.ViewHolder parent class
 * @param <T> The type of item this ViewHolder is storing
 * @author Michael Liu, Genfu Liu
 */
public abstract class GenericViewHolder<T> extends RecyclerView.ViewHolder {

    /**
     * Current context that this view holder is in
     */
    protected Context currentContext;

    /**
     * Constructor to initialize fields and passes the itemView to the parent
     * ALL subclass constructors MUST be public because of the implementations in GenericRecyclerViewAdapter
     * which uses Class.getConstructors() requires the constructor to be publicly accessible
     * @param itemView view of this current item
     */
    protected GenericViewHolder(@NonNull View itemView) {
        super(itemView);

        this.currentContext = itemView.getContext();
    }

    /**
     * Abstract method that will be called when the parent binds a viewholder
     * @param item the type of item this ViewHolder is storing
     */
    public abstract void onBind(T item);
}
