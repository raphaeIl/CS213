package edu.rutgers.rupizzeria.main.managers;

/**
 * This interface is the part of The Observer Pattern that is implemented in the StoreManager class.
 * All classes that implement this method are the subscribers to the observable class which the
 * onAction method will be called when the event in StoreManager triggers
 * @param <T> the type of object that is being changed during the event
 * @author Michael Liu, Genfu Liu
 */
public interface ActionListener<T> {

    /**
     * The observable class calls this method whenever it's events triggers
     * which notifies all classes implementing this interface
     * @param itemChanged The object that was changed after the event
     * @param isRemoved if object is removed or added
     */
    void onAction(T itemChanged, boolean isRemoved);

}
