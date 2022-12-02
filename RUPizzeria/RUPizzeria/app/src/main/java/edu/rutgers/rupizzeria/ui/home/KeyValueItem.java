package edu.rutgers.rupizzeria.ui.home;

import edu.rutgers.rupizzeria.types.Size;

public class KeyValueItem<K, V> {

    private K key;
    private V value;

    private Size size;

    public KeyValueItem(K key, V value, Size size) {
        this.key = key;
        this.value = value;
        this.size = size;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Size getSize() {
        return size;
    }
}
