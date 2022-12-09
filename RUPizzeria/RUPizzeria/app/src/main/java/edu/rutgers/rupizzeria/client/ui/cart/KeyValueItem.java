package edu.rutgers.rupizzeria.client.ui.cart;

import edu.rutgers.rupizzeria.main.core.types.Size;

/**
 * Represents a K type Key and V type value pair
 * mainly used for pairs of items in a recycler view
 * similar to the Map.Entry object in the Map data structure in java
 * @param <K> The key's data type
 * @param <V> The value's data type
 * @author Michael Liu, Genfu Liu
 */
public class KeyValueItem<K, V> {

    /**
     * The key in the pair
     */
    private K key;

    /**
     * The value in the pair
     */
    private V value;

    /**
     * Since this class is used to display pairs in a TextView
     * this size represents the font size
     */
    private Size size;

    /**
     * Constructor to initialize all the fields
     * @param key The key
     * @param value The value
     * @param size The size of the display font
     */
    public KeyValueItem(K key, V value, Size size) {
        this.key = key;
        this.value = value;
        this.size = size;
    }

    /**
     * Getter to get the key
     * @return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * Getter to get the value
     * @return the value
     */
    public V getValue() {
        return value;
    }

    /**
     * Getter to get the size
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Setter to set the key
     * @param key the new value of the key to be set
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Setter to set the value
     * @param value the new value of the value
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Overridden toString method to display the key
     * @return
     */
    @Override
    public String toString() {
        return key.toString();
    }
}
