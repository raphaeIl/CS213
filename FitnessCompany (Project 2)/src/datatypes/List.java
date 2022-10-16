package datatypes;

import utils.Utils;

public class List<T>{

    private T[] array;
    private int size;

    public List() {
        this(0);
    }

    public List(int initialSize) {
        size = initialSize;

        array = (T[]) new Object[size];
    }

    public boolean add(T element) {
        grow();

        array[size++] = element;

        return true;
    }

    public T remove(int index) {
        T removed = array[index];

        shiftLeft(index);
        shrink();

        return removed;
    }

    public T get(int index) {
        return array[index];
    }

    public boolean contains(T element) {
        for (T i : array)
            if (i == element)
                return true;
        return false;
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++)
            if (array[i] == element)
                return i;
        return -1;
    }

    public void clear() {
        size = 0;
        array = (T[]) new Object[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    public Object[] asArray() {
        Object[] asArray = new Object[size];

        for (int i = 0; i < size; i++)
            asArray[i] = array[i];

        return array;
    }

    private void shiftLeft(int startingIndex) {
        for (int i = startingIndex; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
    }

    private void grow() {
        T[] grew = (T[]) new Object[size + 1];

        for (int i = 0; i < size; i++)
            grew[i] = array[i];

        array = grew;
    }

    private void shrink() {
        T[] shrunk = (T[]) new Object[--size];

        for (int i = 0; i < size; i++)
            shrunk[i] = array[i];

        array = shrunk;
    }

    @Override
    public String toString() {
        return "Array [array=" + Utils.arrayToString(array) + ", size=" + size + "]";
    }


}
