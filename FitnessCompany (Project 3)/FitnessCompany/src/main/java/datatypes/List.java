package datatypes;

import utils.Utils;

/**
 * A custom generic list class we created to reduce code duplication,
 * since a list is needed in most databases. Mimics java's ArrayList
 * @param <T> The type that this list is storing
 */
public class List<T>{

    /**
     * The List is implemented with a generic array
     */
    private T[] array;

    /**
     * Size of the list
     */
    private int size;

    /**
     * Basic constructor to create 0 size list
     */
    public List() {
        this(0);
    }

    /**
     * Alternative constructor to create a list with a custom initial size
     * @param initialSize the initial size of the list
     */
    public List(int initialSize) {
        size = initialSize;

        array = (T[]) new Object[size];
    }

    /**
     * Add an element to the list,
     * automatically grows the list if it's full
     * @param element the element to be added
     * @return if the element was successfully added or not
     */
    public boolean add(T element) {
        grow();

        array[size++] = element;

        return true;
    }

    /**
     * Removes an element from the list,
     * automatically shrinks the array since there will be an empty element after removing
     * @param index the index the element to be removed is at
     * @return the removed element
     */
    public T remove(int index) {
        T removed = array[index];

        shiftLeft(index);
        shrink();

        return removed;
    }

    /**
     * Gets a element by it's index
     * @param index the index of the element
     * @return the element
     */
    public T get(int index) {
        return array[index];
    }

    /**
     * Checks to see if the list contains an element
     * @param element the element to be checked
     * @return if the list contains the element
     */
    public boolean contains(T element) {
        for (T i : array)
            if (i == element)
                return true;
        return false;
    }

    /**
     * Gets an element's index
     * @param element the element
     * @return the index of the element, -1 if not found
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++)
            if (array[i] == element)
                return i;
        return -1;
    }

    /**
     * Clears the entire list
     */
    public void clear() {
        size = 0;
        array = (T[]) new Object[0];
    }

    /**
     * Checks to see if the list is empty
     * @return if the list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the size of the list
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Gets the list as a generic Object array,
     * kinda unsafe because casts
     * @return the Object array representing the list
     */
    public Object[] asArray() {
        Object[] asArray = new Object[size];

        for (int i = 0; i < size; i++)
            asArray[i] = array[i];

        return array;
    }

    /**
     * Private Helper method to shift all the elements left once starting from an index,
     * used when shrinking the array
     * @param startingIndex starting from this index, everything to the right will be shifted left once
     */
    private void shiftLeft(int startingIndex) {
        for (int i = startingIndex; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
    }

    /**
     * Grows the list by 1, used when inserting items
     */
    private void grow() {
        T[] grew = (T[]) new Object[size + 1];

        for (int i = 0; i < size; i++)
            grew[i] = array[i];

        array = grew;
    }

    /**
     * Shrinks the list by 1, used when removing items
     */
    private void shrink() {
        T[] shrunk = (T[]) new Object[--size];

        for (int i = 0; i < size; i++)
            shrunk[i] = array[i];

        array = shrunk;
    }

    /**
     * Overriden toString method to get the string representation of the list
     * @return the string representation of the list
     */
    @Override
    public String toString() {
        return Utils.arrayToString(array);
    }

}
