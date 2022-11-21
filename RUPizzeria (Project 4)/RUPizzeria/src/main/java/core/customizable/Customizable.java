package core.customizable;

/**
 * a blueprint for objects that are Customizable
 * @author Michael Liu, Genfu Liu
 */
public interface Customizable {

    /**
     * Functionality that supports adding some object to this Customizable
     * @param obj the object to be added
     * @return if it was added successfully or not
     */
    boolean add(Object obj);

    /**
     * Functionality that supports removing some object from this Customizable
     * @param obj the object to be removed
     * @return if it was removed successfully or not
     */
    boolean remove(Object obj);
}