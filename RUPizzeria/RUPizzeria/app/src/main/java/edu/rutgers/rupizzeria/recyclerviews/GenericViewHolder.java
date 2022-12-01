package edu.rutgers.rupizzeria.recyclerviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public abstract class GenericViewHolder<T> extends RecyclerView.ViewHolder {

    protected Context currentContext;

    protected GenericViewHolder(@NonNull View itemView) {
        super(itemView);

        this.currentContext = itemView.getContext();
    }

    public abstract void onBind(T item);
}
