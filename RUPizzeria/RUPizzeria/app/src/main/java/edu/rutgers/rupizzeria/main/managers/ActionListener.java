package edu.rutgers.rupizzeria.main.managers;

public interface ActionListener<T> {

    void onAction(T itemChanged, boolean isRemoved);

}
